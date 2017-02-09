package com.dx168.mvpcore.core;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yunlong.su on 2017/2/9.
 * 创建retrofit，用retrofit创建Service对象
 */

public class CoreBaseApi {
    private String mBaseUrl;
    private Retrofit mRetrofit;
    private final String TAG = this.getClass().getSimpleName();
    private int default_time = 20_000;

    public CoreBaseApi(String baseUrl) {
        this.mBaseUrl = baseUrl;
        this.mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(getGsonConverterFactory())
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .build();
    }

    protected OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(getLogInterceptor())
                                         .readTimeout(timeOut(), TimeUnit.SECONDS)
                                         .connectTimeout(timeOut(), TimeUnit.SECONDS)
                                         .readTimeout(timeOut(), TimeUnit.SECONDS)
                                         .build();
    }

    @NonNull
    private Interceptor getLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = onSendRequest(chain.request());
                Response response;
                if (isPrintLog()) {
                    //做一个日志的操作
                    long t1 = System.nanoTime();
                    Log.d(TAG, String.format("Send request %s%n%s", request.url(), request.headers()));
                    response = chain.proceed(request);
                    MediaType contentType = response.body().contentType();
                    String body = response.body().string();
                    long t2 = System.nanoTime();
                    Log.d(TAG, String.format("Receive response for %s in %.1fms%n%s%s",
                            response.request().url(),
                            (double) (t2 - t1) / 1000000.0D,
                            response.headers(),
                            body));
                    ResponseBody responseBody = ResponseBody.create(contentType, body);
                    response = response.newBuilder().body(responseBody).build();
                    return response;
                } else {
                    return chain.proceed(request);
                }


            }
        };
    }

    /**
     * 在发送请求的时候可以添加一些内容
     *
     * @param request
     * @return
     */
    protected Request onSendRequest(Request request) {
        return request;
    }

    protected long timeOut() {
        return 40;
    }

    protected boolean isPrintLog() {
        return true;
    }

    @NonNull
    protected GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    
    public <T> T createService(final Class<T> service) {
        final T t = mRetrofit.create(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object obj = method.invoke(t, args);
                if (obj != null && obj instanceof Observable) {
                    Observable observable = (Observable) obj;
                    return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                }
                return obj;
            }
        });
    }
}
