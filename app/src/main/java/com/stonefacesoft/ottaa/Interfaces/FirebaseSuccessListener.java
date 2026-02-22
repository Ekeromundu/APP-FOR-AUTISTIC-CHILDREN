package com.stonefacesoft.ottaa.Interfaces;

public interface FirebaseSuccessListener {

        void onDownloadComplete(int descargaCompleta);
        void onDataFound(int datosEncontrados);
        void onPhotoDownloaded(int fotosDescargadas);
        void onFilesUploaded(boolean subidos);
        void onSuggestedPictosDownloaded(boolean descargado);







}


