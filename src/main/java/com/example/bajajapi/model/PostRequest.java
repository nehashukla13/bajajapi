package com.example.bajajapi.model;

import java.util.List;

public class PostRequest {
    private List<String> data;  // List of strings for your data
    private String fileB64;     // Base64 string for the file

    // Getter and Setter for the 'data' list
    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    // Getter and Setter for the 'fileB64' Base64 string
    public String getFileB64() {
        return fileB64;
    }

    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }
}
