package com.longge.studycollection.banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by yunlong.su on 2017/2/7.
 */

public class Banner extends FrameLayout {

    private List<String> mImageUrls;//网络图片Url
    private List<View> mImageList; //填充ViewPager的控件

    protected Banner(Context context) {
        this(context, null);
    }

    protected Banner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    protected Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createImageView();
        ViewPager viewPager = new ViewPager(context);

        addView(viewPager);
    }

    private void createImageView() {
        for (String imageUrl : mImageUrls) {

        }
    }

    class VpAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }

    class BannerBuilder {
        private List<String> imageUrls;
        private Banner mBanner;

        public BannerBuilder(Context context) {
            if (mBanner == null) {
                mBanner = new Banner(context);
            }
        }

        public BannerBuilder imageList(List<String> imageUrls) {
            this.imageUrls = imageUrls;
            return this;
        }

        public Banner build() {
            mBanner.mImageUrls = imageUrls;
            return mBanner;
        }
    }


}
