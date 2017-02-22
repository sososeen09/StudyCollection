package com.longge.studycollection.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import com.longge.studycollection.permission.internal.PermissionFail;
import com.longge.studycollection.permission.internal.PermissionSuccess;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunlong.su on 2017/2/22.
 */

public class MPermissionUtils {
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String[] permissions) {
        List<String> denied = new ArrayList<>();
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                denied.add(permission);
            }
        }
        return denied;
    }

    /**
     * 通过annotation和requestCode确定Method
     *
     * @param activity
     * @param annotation
     * @param requestCode
     * @return
     */
    public static Method findMethodWithRequestCode(Activity activity, Class<? extends Annotation> annotation, int requestCode) {
        Method[] methods = activity.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                if (annotation == PermissionSuccess.class) {
                    PermissionSuccess methodAnnotation = method.getAnnotation(PermissionSuccess.class);
                    if (methodAnnotation.requestCode() == requestCode) {
                        return method;
                    }
                } else if (annotation == PermissionFail.class) {
                    PermissionFail permissionFail = method.getAnnotation(PermissionFail.class);
                    if (permissionFail.requestCode() == requestCode) {
                        return method;
                    }
                }

            }
        }
        return null;
    }
}
