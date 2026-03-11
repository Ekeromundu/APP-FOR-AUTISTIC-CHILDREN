package com.stonefacesoft.pictogramslibrary.utils;

import android.graphics.drawable.Drawable;

import com.stonefacesoft.pictogramslibrary.Interfaces.ImageDataInterface;
/***
 * <H1>How to use</H1>
 * In order to create one object such as an image<br>
 * In OTTAA Project one picture Image is ordered by the next description :
 * 1) Name :  the locale uri about the pictogram
 * 2) EditedPictogram: the locale uri about the pictogram
 * 3) Url :  
 * */
public class ImageItem implements ImageDataInterface {

    private String name;
    private String editedPictogram;
    private String pictogramsUri;
    private String pushKey;

    public ImageItem(String name){
        this.name=name;

    }


    @Override
    public void setEditedPictogram(String uri) {
        this.editedPictogram=uri;
    }

    @Override
    public void setImageIcon(String uri) {
        this.name=uri;
    }

    @Override
    public void setUriImage(String uri) {
        this.pictogramsUri=uri;
    }

    public String returUriFile(boolean useWeb){
        if(pictogramsUri!=null&& useWeb)
            return pictogramsUri;
        else if(editedPictogram!=null)
            return editedPictogram;
        else
            return name;
    }
}
