package com.longge.studycollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.longge.studycollection.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Banner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBanner = (Banner) findViewById(R.id.banner);
        List<String> mList = new ArrayList<>();
        mBanner.setDataList(mList);
    }
}
