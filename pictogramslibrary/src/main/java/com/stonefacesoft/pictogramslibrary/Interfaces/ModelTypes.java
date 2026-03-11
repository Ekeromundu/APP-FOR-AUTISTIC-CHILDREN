package com.stonefacesoft.pictogramslibrary.Interfaces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import java.io.File;
import java.net.URL;

public interface  ModelTypes<TarjetView> {
    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable Bitmap bitmap, View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable Drawable drawable,View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable String string,View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable Uri uri,View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable File file,View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@RawRes @DrawableRes @Nullable Integer resourceId,View imageView);

    @Deprecated
    @CheckResult
    TarjetView loadDrawable(@Nullable URL url,View imageView);

    @NonNull
    @CheckResult
    TarjetView loadDrawable(@Nullable byte[] model,View imageView);

    @NonNull
    @CheckResult
    @SuppressWarnings("unchecked")
    TarjetView  loadDrawable(@Nullable Object model,View imageView);

}
