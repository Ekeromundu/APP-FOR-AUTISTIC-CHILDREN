package com.stonefacesoft.pictogramslibrary;

import com.google.firebase.storage.FirebaseStorage;

public class CloudStorageManager {
    private FirebaseStorage storage;
    private static CloudStorageManager _CloudStorageManager;
    public synchronized static CloudStorageManager getInstance(){
        if(_CloudStorageManager == null)
            _CloudStorageManager = new CloudStorageManager();
        return _CloudStorageManager;
    }

    public void setStorage(FirebaseStorage storage){
       this.storage = storage;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }
}
