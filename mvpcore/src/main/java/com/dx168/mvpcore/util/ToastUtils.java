package com.dx168.mvpcore.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;


public class ToastUtils {


    private static Context sContext;

    private ToastUtils() {
    }

    public static void init(Context context) {
        sContext = context;
    }

    private static Toast mToast;

    private static Toast getInstance() {
        if (mToast == null) {
            synchronized (ToastUtils.class) {
                if (mToast == null) {
                    mToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
                }
            }
        }
        return mToast;

    }

    public static void Short(@NonNull CharSequence sequence) {
        if (TextUtils.isEmpty(sequence)) return;
        Toast toast = getInstance();
        toast.setText(sequence);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void Short(@StringRes int id) {
        Short(sContext.getString(id));
    }

    public static void Long(@NonNull CharSequence sequence) {
        if (TextUtils.isEmpty(sequence)) return;
        Toast toast = getInstance();
        toast.setText(sequence);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void Long(@StringRes int id) {
        Long(sContext.getString(id));
    }

}
