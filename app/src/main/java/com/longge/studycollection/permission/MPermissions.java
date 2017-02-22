package com.longge.studycollection.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import com.longge.studycollection.permission.internal.PermissionFail;
import com.longge.studycollection.permission.internal.PermissionSuccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunlong.su on 2017/2/21.
 * 要保证兼容性，分23和23以下
 */

public class MPermissions {

    public static void needPermissions(Activity activity, int requestCode, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //只有Android6.0以上才有运行时权限
            List<String> denied = MPermissionUtils.findDeniedPermissions(activity, permissions);
            if (denied.size() > 0) {
                activity.requestPermissions(denied.toArray(new String[denied.size()]), requestCode);
            } else {
                doSomethingSuccess(activity, requestCode);
            }

        } else {
            doSomethingSuccess(activity, requestCode);
        }

    }


    public static void needPermission(Activity activity, int code, String permission) {
        needPermissions(activity, code, new String[]{permission});
    }


    /**
     * 如果有被拒绝的授权，就认为失败了，进行失败的处理
     * 如果全部授权才认为是授权成功
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        List<String> deniedPermissions = new ArrayList<>();

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                //被拒绝授权
                deniedPermissions.add(permissions[i]);
            }
        }

        if (deniedPermissions.size() > 0) {
            doSomethingFailed(activity, requestCode);
        } else {
            doSomethingSuccess(activity, requestCode);
        }


    }


    private static void doSomethingSuccess(Activity activity, int requestCode) {
        Method method = MPermissionUtils.findMethodWithRequestCode(activity, PermissionSuccess.class, requestCode);
        executeMethod(activity, method);
    }

    private static void doSomethingFailed(Activity activity, int requestCode) {
        Method method = MPermissionUtils.findMethodWithRequestCode(activity, PermissionFail.class, requestCode);
        executeMethod(activity, method);
    }

    private static void executeMethod(Activity activity, Method method) {
        if (activity != null && method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }

            try {
                method.invoke(activity, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
