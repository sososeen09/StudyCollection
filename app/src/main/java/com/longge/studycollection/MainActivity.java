package com.longge.studycollection;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.longge.studycollection.banner.Banner;
import com.longge.studycollection.permission.MPermissions;
import com.longge.studycollection.permission.internal.PermissionFail;
import com.longge.studycollection.permission.internal.PermissionSuccess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Banner mBanner;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mBanner = (Banner) findViewById(R.id.banner);
//        List<String> mList = new ArrayList<>();
//        mBanner.setDataList(mList);

//        startActivity(new Intent(this, LazyActivity.class));
//
//        finish();
    }

    public void saveFile(View view) {
        File directory = Environment.getExternalStorageDirectory();
        File file = new File(directory, "test.txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write("测试写的权限");
            bufferedWriter.flush();
            bufferedWriter.close();
            Log.d(TAG, "saveFile: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInternalFile(View view) {
        File directory = getFilesDir();
        File file = new File(directory, "test.txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write("测试写如内部的权限");
            bufferedWriter.flush();
            bufferedWriter.close();
            Log.d(TAG, "saveInternalFile: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPermissions(View view) {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "should permission write external storage", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }

//        }

    }

    public void getSDCardPermission(View view) {
        MPermissions.needPermission(this, 111, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void getContactPermission(View view) {
        MPermissions.needPermission(this, 112, Manifest.permission.READ_CONTACTS);
    }

    @PermissionSuccess(requestCode = 111)
    public void onSDCardPermisionSuccess() {
        Toast.makeText(this, "onSDCardPermisionSuccess", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 111)
    public void onSDCardPermisionFail() {
        Toast.makeText(this, "onSDCardPermisionFail", Toast.LENGTH_SHORT).show();

    }

    @PermissionSuccess(requestCode = 112)
    public void onContactPermisionSuccess() {
        Toast.makeText(this, "onContactPermisionSuccess", Toast.LENGTH_SHORT).show();

    }

    @PermissionFail(requestCode = 112)
    public void onContactPermisionFail() {
        Toast.makeText(this, "onContactPermisionFail", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200:
                Log.d(TAG, "onRequestPermissionsResult: ");
                break;
        }
    }

}
