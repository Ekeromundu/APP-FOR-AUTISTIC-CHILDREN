package com.stonefacesoft.pictogramslibrary.utils;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public class ValidateContext {


    public static boolean isValidContext(Context context) {
        Activity activity = getmActivityFromContext(context);
        if (activity == null){
            return false;
        }
        return !isActivityDestroyed(activity);
    }

    /**
     * @return call the activity by context and return that
     */
    private static Activity getmActivityFromContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }


    /**
     * @return show if the activity exist or not.
     */
    private static boolean isActivityDestroyed(Activity activity) {
        return activity.isDestroyed() || activity.isFinishing() || activity.isChangingConfigurations();
    }


}
