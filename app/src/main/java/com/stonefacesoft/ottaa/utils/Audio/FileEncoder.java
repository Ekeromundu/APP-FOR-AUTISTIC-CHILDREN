package com.stonefacesoft.ottaa.utils.Audio;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.media3.common.MediaItem;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.Transformer;
import androidx.media3.common.MimeTypes;

import java.io.File;
import java.io.IOException;

public class FileEncoder {

    private Transformer transformer;
    private final Context mContext;

    public FileEncoder(Context mContext) {
        this.mContext = mContext;
    }

    /*
     * That method transform the file
     */
    public void encodeAudioFile(Transformer.Listener listener, String filePath, String locationPath) {
        Handler handler = new Handler(mContext.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                TransformationRequest transformationRequest = new TransformationRequest.Builder().build();
                transformationRequest.buildUpon().setAudioMimeType(MimeTypes.AUDIO_OGG);
                transformer = new Transformer.Builder(mContext).setLooper(Looper.myLooper())
                        .setTransformationRequest(transformationRequest).addListener(listener).build();
                MediaItem mediaItem = MediaItem.fromUri(Uri.fromFile(new File(filePath)));
                transformer.start(mediaItem, locationPath);

            }
        });

    }

}
