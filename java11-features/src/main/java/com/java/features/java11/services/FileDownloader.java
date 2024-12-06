package com.java.features.java11.services;

import com.java.features.java11.models.ProgressListener;

public class FileDownloader {
    public byte[] downloadFile(String url, ProgressListener listener) {
        // Simulate file download
        listener.onProgress(100, 100);
        return new byte[100]; // Dummy file content
    }
}
