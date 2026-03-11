package com.stonefacesoft.ottaa.Interfaces;

import androidx.media3.transformer.Transformer;

public interface AudioTransformationListener {
    void startAudioTransformation(Transformer.Listener listener, String filePath, String locationPath);
}
