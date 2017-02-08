package com.longge.studycollection.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunlong.su on 2017/2/7.
 */

public class Banner extends RelativeLayout {

    private final Context mContext;
    private List<String> mImageUrls = new ArrayList<>();//网络图片Url
    private List<ImageView> mImageList = new ArrayList<>(); //填充ViewPager的控件，大部分情况应该是ImageView
    private List<ImageView> mIndicatorList = new ArrayList<>();//存放Indicator的集合
    private LinearLayout mLlIndicator; //填充Indicator的LinearLayout


    private ImageLoader mImageLoader;
    private VpAdapter mAdapter;

    protected Banner(Context context) {
        this(context, null);
    }

    protected Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        createImageView();


        initView(context);
    }

    private void initView(Context context) {
        initViewPager(context);
        initIndicator(context);
    }

    private void initIndicator(Context context) {
        LinearLayout linearLayout = new LinearLayout(mContext);
    }

    private void initViewPager(Context context) {
        ViewPager viewPager = new ViewPager(context);
        mAdapter = new VpAdapter();
        viewPager.setAdapter(mAdapter);
        addView(viewPager);
    }

    private void createImageView() {
        ImageView imageView;
        for (int i = 0; i < mImageUrls.size(); i++) {
            imageView = new ImageView(mContext);
            mImageList.add(imageView);
        }
    }

    /**
     * 设置数据
     *
     * @param mList
     */
    public void setDataList(List<String> mList) {
        mImageUrls = mList;
        notifyDataChanged();
    }

    private void notifyDataChanged() {


    }

    public void setImageLoader(ImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    class VpAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mImageList.get(position);
            mImageLoader.display(container.getContext(), mImageList.get(position), mImageUrls.get(position));
            container.addView(view);
            return view;
        }
    }


}
