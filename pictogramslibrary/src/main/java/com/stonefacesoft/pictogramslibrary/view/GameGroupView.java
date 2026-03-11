package com.stonefacesoft.pictogramslibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.stonefacesoft.pictogramslibrary.Classes.GameGroup;
import com.stonefacesoft.pictogramslibrary.Classes.OTTAAProjectObjects;
import com.stonefacesoft.pictogramslibrary.R;
import com.stonefacesoft.pictogramslibrary.utils.ValidateContext;

import java.io.File;

public class GameGroupView extends TarjetView{
    private ImageView tagScore;
    private ImageView Color;
    private int Custom_Color;
    private GameGroup pictogramsLibraryGameGroup;

    public GameGroupView(@NonNull Context context) {
        super(context);
        init();
    }

    public GameGroupView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Custom_Picto,
                0, 0);
        try {
            Custom_Texto = a.getString(R.styleable.Custom_Picto_Texto);
            Custom_Color = a.getColor(R.styleable.Custom_Picto_Color, getResources().getColor(R.color.Black));
            IconHeight = a.getInt(R.styleable.Custom_Picto_IconHeight,300);
            IconWidth = a.getInt(R.styleable.Custom_Picto_IconWidth,300);
            //    Custom_Imagen = a.getInteger(R.styleable.Custom_Picto_Imagen, 0);
        } finally {
            a.recycle();
        }
        init();
    }

    public GameGroupView(@NonNull  Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Custom_Picto,
                0, 0);
        try {
            Custom_Texto = a.getString(R.styleable.Custom_Picto_Texto);
            Custom_Color = a.getColor(R.styleable.Custom_Picto_Color, getResources().getColor(R.color.Black));
            IconHeight = a.getInt(R.styleable.Custom_Picto_IconHeight,300);
            IconWidth = a.getInt(R.styleable.Custom_Picto_IconWidth,300);
            //    Custom_Imagen = a.getInteger(R.styleable.Custom_Picto_Imagen, 0);
        } finally {
            a.recycle();
        }
        init();
    }



    private void init() {
        inflate(getContext(), R.layout.gamegroup, this);
        this.StrTittle = findViewById(R.id.grid_text);
        this.icon = findViewById(R.id.grid_image);
        this.Color = findViewById(R.id.color_Picto);
        this.kind_of_pictogram = findViewById(R.id.kind_of_Picto);
        this.tagScore = findViewById(R.id.score);
    }

    public void setPictogramsLibraryGameGroup(GameGroup pictogramsLibraryGameGroup) {
        this.pictogramsLibraryGameGroup = pictogramsLibraryGameGroup;
        setData();
    }

    protected void setData() {
        id = pictogramsLibraryGameGroup.getId();
        setCustom_Texto(pictogramsLibraryGameGroup.getObjectName());
        if(ValidateContext.isValidContext(mContext)){
            if (pictogramsLibraryGameGroup.getEditedPictogram().isEmpty()) {
                Drawable drawable = findResource(pictogramsLibraryGameGroup.getPictogram());
                glideAttatcher.setHeight(IconHeight).setWidth(IconWidth).useDiskCacheStrategy().loadDrawable(drawable, this.icon);
            } else {
                selectIcon(pictogramsLibraryGameGroup, this.icon,glideAttatcher);
            }
        }
    }

    @Override
    protected Drawable findResource() {
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_baseline_cloud_download_24);
        try{
            drawable = mContext.getResources().getDrawable(mContext.getResources().getIdentifier(pictogramsLibraryGameGroup.getPictogram(),"drawable",mContext.getPackageName()));
        }catch (Exception ex){
        }
        return drawable;
    }

    public void setCustom_Texto(String t) {
        this.Custom_Texto = t;
        StrTittle.setText(t);
        invalidate();
        requestLayout();
    }

    public void setCustom_Img(Drawable imagen) {
        Glide.with(getContext()).load(imagen).into(icon);
        this.Custom_Imagen = imagen;
        invalidate();
        requestLayout();
    }


    public void setTagScore(ImageView tagScore) {
        this.tagScore = tagScore;
    }

    public void setDrawableScore(Drawable drawable){
        this.tagScore.setImageDrawable(drawable);
    }



    @Override
    public void setData(int height, int with) {
        id = pictogramsLibraryGameGroup.getId();
        setCustom_Texto(pictogramsLibraryGameGroup.getObjectName());
        if(ValidateContext.isValidContext(mContext)){
            if (pictogramsLibraryGameGroup.getEditedPictogram().isEmpty()) {
                Drawable drawable = findResource(pictogramsLibraryGameGroup.getPictogram());
                glideAttatcher.setHeight(height).setWidth(with).useDiskCacheStrategy().loadDrawable(drawable, this.icon);
            } else {
                selectIcon(pictogramsLibraryGameGroup, this.icon,glideAttatcher);
            }
        }
     }



}
