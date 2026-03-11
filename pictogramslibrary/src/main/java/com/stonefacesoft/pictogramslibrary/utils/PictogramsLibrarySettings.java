package com.stonefacesoft.pictogramslibrary.utils;

public class PictogramsLibrarySettings {
    private boolean debugMode;
    private static PictogramsLibrarySettings _PictogramsLibrarySettings;
    private String packageName;

    public static synchronized PictogramsLibrarySettings getInstance(){
        if(_PictogramsLibrarySettings == null)
            _PictogramsLibrarySettings = new PictogramsLibrarySettings();
        return _PictogramsLibrarySettings;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }




}
