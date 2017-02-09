package com.longge.studycollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.longge.studycollection.banner.Banner;
import com.longge.studycollection.lazy.LazyActivity;

public class MainActivity extends AppCompatActivity {

    Banner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mBanner = (Banner) findViewById(R.id.banner);
//        List<String> mList = new ArrayList<>();
//        mBanner.setDataList(mList);

        startActivity(new Intent(this, LazyActivity.class));

        finish();
    }
}
