package com.stonefacesoft.pictogramslibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.stonefacesoft.pictogramslibrary.Classes.OTTAAProjectObjects;
import com.stonefacesoft.pictogramslibrary.R;
import com.stonefacesoft.pictogramslibrary.utils.GlideAttatcher;
import com.stonefacesoft.pictogramslibrary.utils.ValidateContext;

import java.io.File;
import java.io.IOException;

public class TarjetView extends ConstraintLayout  {
    protected TextView StrTittle;
    protected ImageView icon;
    protected ImageView Color;
    protected volatile ImageView kind_of_pictogram;
    protected int id;
    protected String Custom_Texto;
    protected Drawable Custom_Imagen;
    protected GlideAttatcher glideAttatcher;
    protected Context mContext;
    protected int IconHeight;
    protected int IconWidth;
    protected static final float BYTES_PER_PX = 4.0f; //32 bit


    public TarjetView(@NonNull Context context) {
        super(context);
        this.mContext=context;

    }

    public TarjetView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public TarjetView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TarjetView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setUpContext(Context mContext){
        this.mContext=mContext;
    }

    public void setUpGlideAttatcher(Context mContext){
        glideAttatcher=new GlideAttatcher(mContext);
    }

    public GlideAttatcher getGlideAttatcher() {
        return glideAttatcher;
    }

    public ImageView getKindOfPictogramImageView(){
        return kind_of_pictogram;
    }

    public void changeKindOfPictogramVisibility(){
       if(kind_of_pictogram.getVisibility()==VISIBLE)
        kind_of_pictogram.setVisibility(GONE);
       else
        kind_of_pictogram.setVisibility(VISIBLE);
    }

    public void selectIcon(){

    }

    protected void selectIcon(final OTTAAProjectObjects object,ImageView icon,GlideAttatcher glideAttatcher) {
      String path  = object.getEditedPictogram();
      File picture=new File(path);
      if(ValidateContext.isValidContext(mContext)){
        if(picture.exists())
              glideAttatcher.useDiskCacheStrategy().loadDrawable(picture,icon);
        else{
              glideAttatcher.useDiskCacheStrategy().loadDrawable(Uri.parse(object.getUrl()),icon);
        }
      }
    }

    public void setGlideAttatcher(GlideAttatcher glideAttatcher) {
        this.glideAttatcher = glideAttatcher;
    }



    @Override
    public void destroyDrawingCache() {
        super.destroyDrawingCache();
    }

    protected Drawable findResource(){
        return null;
    }

    protected Drawable findResource( final String name) {
        Drawable aux = mContext.getResources().getDrawable(R.drawable.ic_baseline_cloud_download_24);
        Drawable drawable = aux;
        try{
            drawable = mContext.getResources().getDrawable(mContext.getResources().getIdentifier(name,"drawable",mContext.getPackageName()));
        }catch (Exception ex){
            drawable = aux;
        }
        return drawable;
    }
    protected  void setData(){

    }

    public void setData(int height,int with){

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(glideAttatcher!=null){
            glideAttatcher.destroy();
        }
    }

    public void setIconHeight(int iconHeight) {
        this.IconHeight = iconHeight;
    }

    public void setIconWidth(int iconWidth) {
        this.IconWidth = iconWidth;
    }


}
