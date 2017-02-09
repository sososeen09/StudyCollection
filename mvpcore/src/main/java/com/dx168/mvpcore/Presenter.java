package com.dx168.mvpcore;

/**
 * Created by yunlong.su on 2017/2/7.
 */

public interface Presenter<T extends MvpView> {
    void attachView(T view);

    void detachView();

    void start();
}
