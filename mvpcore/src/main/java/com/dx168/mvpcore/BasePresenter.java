package com.dx168.mvpcore;

/**
 * Created by yunlong.su on 2017/2/7.
 */

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public T getView() {
        return mView;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
