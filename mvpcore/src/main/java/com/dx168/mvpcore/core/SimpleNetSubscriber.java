package com.dx168.mvpcore.core;

import rx.Subscriber;

/**
 * Created by yunlong.su on 2016/12/20.
 */

public abstract class SimpleNetSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFailure(e);
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    public abstract void onResponse(T t);

    public abstract void onFailure(Throwable e);
}
