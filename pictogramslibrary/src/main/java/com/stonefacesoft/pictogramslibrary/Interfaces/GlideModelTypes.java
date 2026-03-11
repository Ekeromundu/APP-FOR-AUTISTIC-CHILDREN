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

import com.stonefacesoft.pictogramslibrary.R;

import java.io.File;
import java.net.URL;

public interface GlideModelTypes<GlideAttatcher> {
    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable Bitmap bitmap, ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable Drawable drawable, ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable String string,ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable Uri uri, ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable File file, ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@RawRes @DrawableRes @Nullable Integer resourceId, ImageView imageView);

    @Deprecated
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable URL url, ImageView imageView);

    @NonNull
    @CheckResult
    GlideAttatcher loadDrawable(@Nullable byte[] model,ImageView imageView);

    @NonNull
    @CheckResult
    @SuppressWarnings("unchecked")
    GlideAttatcher  loadDrawable(@Nullable Object model,ImageView imageView);



}
