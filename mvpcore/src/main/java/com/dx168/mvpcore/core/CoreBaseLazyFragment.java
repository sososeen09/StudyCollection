package com.dx168.mvpcore.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * 懒加载机制
 */
public abstract class CoreBaseLazyFragment extends Fragment {

    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected boolean isVisible = false;
    private boolean isPrepared = false;
    private boolean isLoaded = false;

    public CoreBaseLazyFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && !isLoaded && isPrepared) {
            //可见
            lazyLoadData();
            isLoaded = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        initPrepare();
    }


    /**
     * 懒加载
     */
    private void lazyLoadData() {
        initData();
    }

    /**
     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
     */
    protected void initPrepare() {
    }

    /**
     * 这里获取数据，刷新界面
     */
    protected abstract void initData();

    /**
     * 布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);


}
