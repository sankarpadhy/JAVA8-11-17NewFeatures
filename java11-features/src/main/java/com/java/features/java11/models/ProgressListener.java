package com.java.features.java11.models;

public interface ProgressListener {
    void onProgress(long bytesRead, long totalBytes);
}
