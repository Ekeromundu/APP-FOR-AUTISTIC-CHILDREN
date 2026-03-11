package com.stonefacesoft.pictogramslibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.stonefacesoft.pictogramslibrary.CloudStorageManager;
import com.stonefacesoft.pictogramslibrary.Interfaces.GlideModelTypes;
import com.stonefacesoft.pictogramslibrary.R;

import java.io.File;
import java.net.URL;

import static com.stonefacesoft.pictogramslibrary.utils.ValidateContext.isValidContext;

import javax.security.auth.login.LoginException;

public class GlideAttatcher implements GlideModelTypes {
    private  Glide glide;
    private Context mContext;
    private volatile int height = 300;
    private volatile int width = 300;
    private int radius = 20;
    private int scaleType=0;
    private boolean useDiskCacheStrategy;
    private boolean useCornerRadious;
    private boolean useDecodeFormat =  false;



    public GlideAttatcher(final @NonNull Context mContext) {
        this.mContext = mContext;
        getGlide();
    }

    public GlideAttatcher loadContext(final @NonNull Context mContext){
        this.mContext = mContext;
        glide = Glide.get(this.mContext);
        return this;
    }

    /*
    * Use this flag in order to set an scaleoption
    * */
    public GlideAttatcher setScale(int scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    public GlideAttatcher useDiskCacheStrategy(){
        this.useDiskCacheStrategy = true;
        return this;
    }

    public GlideAttatcher useDecodeFormatRGB565(){
        this.useDecodeFormat = true;
        return this;
    }

    /**
     * clean the glide memory if you need
     */
    public void clearMemory() {
        if(!isValidContext(mContext))
            glide.clearMemory();
    }


    private Glide getGlide() {
        if(isValidContext(this.mContext)&& glide == null)
            glide = Glide.get(this.mContext);
        return glide;
    }

    /**
     * verify the Glide context
     *
     * @return true if the activity exist or false when the activity not exist
     */


    public GlideAttatcher setHeight(int height) {
        this.height = height;
        return this;
    }

    public GlideAttatcher setWidth(int width) {
        this.width = width;
        return this;
    }

    public GlideAttatcher setRadius(int radius) {
        this.radius = radius;
        return this;
    }
    /**
     * Use this option in order to create a corner radious pictogram
     * */
    public GlideAttatcher UseCornerRadius(boolean useCornerRadious) {
        this.useCornerRadious = useCornerRadious;
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable Bitmap bitmap, ImageView imageView) {
        if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(bitmap)))))).fallback(R.drawable.ic_baseline_cloud_download_24).into(imageView).waitForLayout();
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable Drawable drawable, ImageView imageView) {
           if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(drawable)))))).into(imageView).waitForLayout();
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable String string, ImageView imageView) {
        if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(string)))))).into(imageView).waitForLayout();
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable Uri uri, ImageView imageView) {

        if(isValidContext(mContext)){
            if(Build.VERSION.SDK_INT<= Build.VERSION_CODES.M){
                glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(uri)))))).error(R.drawable.ic_baseline_cloud_download_24).into(imageView).waitForLayout();
            }else{
                glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(uri).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            loadOnlineMode(uri+"",imageView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).error(R.drawable.ic_baseline_cloud_download_24)))))).into(imageView).waitForLayout();
            }
        }
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable File file, ImageView imageView) {
        if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(file)))))).into(imageView).waitForLayout();
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable Integer resourceId, ImageView imageView) {
        if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(resourceId)))))).into(imageView).waitForLayout();
        return this;
    }

    @Override
    public Object loadDrawable(@Nullable URL url, ImageView imageView) {
        if(isValidContext(mContext)){
            if(Build.VERSION.SDK_INT<= Build.VERSION_CODES.M){
                glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(url)))))).error(R.drawable.ic_baseline_cloud_download_24).into(imageView).waitForLayout();
            }else{
                glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(url).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.M)
                            loadOnlineMode(url+"",imageView);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).error(R.drawable.ic_baseline_cloud_download_24)))))).into(imageView).waitForLayout();
            }

        }    return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable byte[] model, ImageView imageView){
        if(isValidContext(mContext)) {
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(model)))))).into(imageView).waitForLayout();
        }
        return this;
    }

    @NonNull
    @Override
    public Object loadDrawable(@Nullable Object model, ImageView imageView) {
        if(isValidContext(mContext))
            glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(model)))))).into(imageView).waitForLayout();
        return this;
    }






    /**
     * scale option method
     * 0 default value
     * 1 center inside
     * 2 center crop
     * 3 fitcenter
     * 4 option center inside
     * 5 option center crop
     * 6 option center fit
     */
    public RequestBuilder<Drawable> glideScaleItem(RequestBuilder<Drawable> drawableRequestBuilder) {
        switch (scaleType) {
            case 0:
                return drawableRequestBuilder;
            case 1:
                return drawableRequestBuilder.centerInside();
            case 2:
                return drawableRequestBuilder.centerCrop();
            case 3:
                return drawableRequestBuilder.fitCenter();
            case 4:
                return drawableRequestBuilder.optionalCenterInside();
            case 5:
                return drawableRequestBuilder.optionalCenterCrop();
            case 6:
                return drawableRequestBuilder.optionalFitCenter();
            default:
                return drawableRequestBuilder;
        }
    }

    public RequestBuilder<Drawable> overrideMethod(RequestBuilder<Drawable> drawableRequestBuilder){
        return drawableRequestBuilder.override(Target.SIZE_ORIGINAL);
    }

    public RequestBuilder<Drawable> cornerRadious(RequestBuilder<Drawable> drawableRequestBuilder) {
        if (useCornerRadious)
            return drawableRequestBuilder.transform(new RoundedCorners(radius));
        return drawableRequestBuilder;
    }

    public RequestBuilder<Drawable>  useDiskCacheStrategic(RequestBuilder<Drawable> drawableRequestBuilder){
        if(useDiskCacheStrategy)
            return drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        return drawableRequestBuilder;
    }

    public RequestBuilder<Drawable> useDecodeFormat(RequestBuilder<Drawable> drawableRequestBuilder){
        if(useDecodeFormat){
            return drawableRequestBuilder.format(DecodeFormat.PREFER_RGB_565);
        }
        return drawableRequestBuilder;
    }

    private ActivityManager.MemoryInfo getAvailableMemory(){
            ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
    }

    public void destroy(){
        if(mContext!=null) {
            try{
                getGlide().clearMemory();
                Glide.with(mContext).onDestroy();
            }catch (Exception ex){

            }
        }
    }

    public void forceClearMemory(){
        try{
        if(glide!=null)
            glide.clearMemory();
        }catch (Exception ex){
            Log.e(this.getClass().getName(), "forceClearMemory: ",ex.getCause() );
        }
    }

    public GlideAttatcher setUseDecodeFormat(boolean useDecodeFormat) {
        this.useDecodeFormat = useDecodeFormat;
        return this;
    }

    private void loadOnlineMode(String url,ImageView imageView){
        if(url.toString().contains("firebasestorage")){
            String aux = url.toString();
            if(aux.contains("?alt")){
                aux = aux.substring(0,aux.lastIndexOf("?alt"));
            }
            if(CloudStorageManager.getInstance().getStorage()!=null){
                StorageReference mRef = CloudStorageManager.getInstance().getStorage().getReferenceFromUrl(aux);
                mRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uriResult) {
                        glideScaleItem(overrideMethod(cornerRadious(useDecodeFormat(useDiskCacheStrategic(getGlide().getRequestManagerRetriever().get(mContext).load(uriResult).error(R.drawable.ic_baseline_cloud_download_24)))))).into(imageView).waitForLayout();
                    }
                });
            }
        }
    }
}
