package com.longge.studycollection.lazy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by yunlong.su on 2017/2/9.
 */

public class LazyFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private int position;
    private boolean hasLoaded = false;
    private boolean isPrepared = false;

    private boolean Debug = false;

    public static LazyFragment getInstance(int position) {
        LazyFragment lazyFragment = new LazyFragment();
        lazyFragment.setPosition(position);
        return lazyFragment;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Debug)
            Log.d(TAG, "onAttach: " + position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Debug)
            Log.d(TAG, "onCreate: " + position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (Debug)
            Log.d(TAG, "onCreateView: " + position);
        if (position % 2 != 0) {
            Button button = new Button(container.getContext());
            button.setText("position: " + position);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(container.getContext(), AnotherActivity.class));
                }
            });
            return button;
        } else {
            TextView textView = new TextView(container.getContext());
            textView.setText("position: " + position);
            return textView;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Debug)
            Log.d(TAG, "onViewCreated: " + position);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Debug)
            Log.d(TAG, "onActivityCreated: " + position);
        isPrepared = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Debug)
            Log.d(TAG, "onStart: " + position);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Debug)
            Log.d(TAG, "onResume: " + position);
        //为了调用一次lazyLoadData方法
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Debug)
            Log.d(TAG, "onPause: " + position);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Debug)
            Log.d(TAG, "onStop: " + position);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Debug)
            Log.d(TAG, "onDestroyView: " + position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Debug)
            Log.d(TAG, "onDestroy: " + position);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if (Debug)
            Log.d(TAG, "onDetach: " + position);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (Debug)
            Log.d(TAG, "setUserVisibleHint: " + position + "  " + isVisibleToUser);

        if (isVisibleToUser && !hasLoaded && isPrepared) {
            //可见
            lazyLoadData();
            hasLoaded = true;
        }
    }

    @Override
    public boolean getUserVisibleHint() {
        if (Debug)
            Log.d(TAG, "getUserVisibleHint: " + position);
        return super.getUserVisibleHint();
    }

    protected void lazyLoadData() {
        Log.d(TAG, "lazyLoadData: " + position);
    }


}

