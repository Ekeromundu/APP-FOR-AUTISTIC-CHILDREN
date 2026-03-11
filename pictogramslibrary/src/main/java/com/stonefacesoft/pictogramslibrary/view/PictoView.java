package com.stonefacesoft.pictogramslibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.stonefacesoft.pictogramslibrary.Classes.OTTAAProjectObjects;
import com.stonefacesoft.pictogramslibrary.Classes.Pictogram;
import com.stonefacesoft.pictogramslibrary.R;
import com.stonefacesoft.pictogramslibrary.utils.GlideAttatcher;
import com.stonefacesoft.pictogramslibrary.utils.MemoryUtils;
import com.stonefacesoft.pictogramslibrary.utils.ValidateContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class PictoView extends TarjetView{

    private boolean isClicked;
    private int Custom_Color;
    private volatile Pictogram pictogramsLibraryPictogram;
    private boolean useDiskCacheStrategy,useDecodeFormata355;
    private boolean useGlideAttatcher;

    public PictoView(@NonNull Context context) {
        super(context);
        init();
    }

    public PictoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Custom_Picto,
                0, 0);
        try {
            Custom_Texto = a.getString(R.styleable.Custom_Picto_Texto);
            Custom_Color = a.getColor(R.styleable.Custom_Picto_Color, getResources().getColor(R.color.Black));
            IconHeight = a.getInt(R.styleable.Custom_Picto_IconHeight,100);
            IconWidth = a.getInt(R.styleable.Custom_Picto_IconWidth,100);
            //            Custom_Imagen = a.getInteger(R.styleable.Custom_Picto_Imagen, 0);
        } finally {
            a.recycle();
        }
        init();
    }

    public PictoView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Custom_Picto,
                0, 0);

        try {
            Custom_Texto = a.getString(R.styleable.Custom_Picto_Texto);
            Custom_Color = a.getColor(R.styleable.Custom_Picto_Color, getResources().getColor(R.color.Black));
            IconHeight = a.getInt(R.styleable.Custom_Picto_IconHeight,100);
            IconWidth = a.getInt(R.styleable.Custom_Picto_IconWidth,100);
//            Custom_Imagen = a.getInteger(R.styleable.Custom_Picto_Imagen, 0);
        } finally {
            a.recycle();
        }
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.pictogram, this);
        this.StrTittle = findViewById(R.id.grid_text);
        this.icon = findViewById(R.id.Imagen_Picto);
        this.kind_of_pictogram = findViewById(R.id.kind_of_Picto);
        this.Color = findViewById(R.id.color_Picto);
    }
    /**
     * Set the color of the pictogram
     * */
    public void setCustom_Color(Integer color) {
        this.Custom_Color = color;
        //Img.setBackgroundColor(color);
        Color.setColorFilter(color);
        invalidate();
        requestLayout();
    }

    /**
     * set the icon of the pictogram
     * */
    public void setCustom_Img(Drawable imagen) {
        this.Custom_Imagen = imagen;
        icon.setImageDrawable(imagen);
        invalidate();
        requestLayout();
    }

    public void setKindOfPictogram(Drawable drawable){
        this.kind_of_pictogram.setImageDrawable(drawable);
        invalidate();
        requestLayout();
    }

    /**
     * Set up the name of the pictogram
     * */
    public void setCustom_Texto(String t) {
        this.Custom_Texto = t;
        StrTittle.setText(t);
        invalidate();
        requestLayout();
    }

    public String getCustom_Texto()  {return Custom_Texto;}

    public int getCustom_Color()  {return Custom_Color;}

    public Drawable getCustom_Imagen()  {return Custom_Imagen;}

    public void goneCustomTexto(){
        StrTittle.setVisibility(GONE);
    }
    public void setInvisibleCustomTexto(){
        StrTittle.setVisibility(INVISIBLE);
    }

    public void setVisibleText(){
        StrTittle.setVisibility(VISIBLE);
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isClicked() {
        return isClicked;
    }
    /**
     * Set up the id of the pictogram
     * */
    public void setIdPictogram(int id) {
        this.id = id;
    }


    public int getIdPictogram() {
        return id;
    }
    public void setPictogramsLibraryPictogram(Pictogram grupo) {
        this.pictogramsLibraryPictogram = grupo;
        setData();
    }

    public void setPictogramsLibraryPictogram(Pictogram pictogram, GlideAttatcher glideAttatcher){
        this.pictogramsLibraryPictogram = pictogram;
        this.glideAttatcher = glideAttatcher;
        setData();
    }


    public ImageView getImageView(){
        return icon;
    }

    protected void setData(){
        id= pictogramsLibraryPictogram.getId();
        this.setCustom_Texto(pictogramsLibraryPictogram.getObjectName());
        this.icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        cargarColor(pictogramsLibraryPictogram.getType());
        if(ValidateContext.isValidContext(mContext)){
            if(pictogramsLibraryPictogram.getEditedPictogram().isEmpty()){
                if(!pictogramsLibraryPictogram.getPictogram().startsWith("https://")){
                    Drawable drawable = findResource(pictogramsLibraryPictogram.getPictogram());
                    glideAttatcher.setWidth(IconWidth).setHeight(IconHeight).useDiskCacheStrategy().loadDrawable(drawable,this.icon);
                }else{
                    glideAttatcher.useDiskCacheStrategy().loadDrawable(Uri.parse(pictogramsLibraryPictogram.getPictogram()),this.icon);
                }
            }else{
                selectIcon(pictogramsLibraryPictogram,this.icon,glideAttatcher);
            }
        }
    }







    @Override
    public void selectIcon(){
        try{
            String path  = pictogramsLibraryPictogram.getEditedPictogram();
            File picto=new File(path);
            if(ValidateContext.isValidContext(mContext)) {
                if (picto.exists())
                    glideAttatcher.useDiskCacheStrategy().loadDrawable(picto, this.icon);
                else
                    glideAttatcher.useDiskCacheStrategy().loadDrawable(Uri.parse(pictogramsLibraryPictogram.getUrl()), this.icon);
            }
        }catch (Exception ex){
            if(ValidateContext.isValidContext(mContext))
                glideAttatcher.useDiskCacheStrategy().loadDrawable(Uri.parse(pictogramsLibraryPictogram.getUrl()),this.icon);
        }
    }


    private void cargarColor(int color) {
        switch (color) {
            case 1:
                setCustom_Color(getResources().getColor(R.color.Yellow));
                break;
            case 2:
               setCustom_Color(getResources().getColor(R.color.Orange));
                break;
            case 3:
                setCustom_Color(getResources().getColor(R.color.YellowGreen));
                break;
            case 4:
                setCustom_Color(getResources().getColor(R.color.DodgerBlue));
                break;
            case 5:
                setCustom_Color(getResources().getColor(R.color.Magenta));
                break;
            case 6:
                setCustom_Color(getResources().getColor(R.color.Black));
                break;
            default:
                setCustom_Color(getResources().getColor(R.color.Black));
                break;
        }
    }


    public Pictogram getPictogram() {
        return pictogramsLibraryPictogram;
    }


}
