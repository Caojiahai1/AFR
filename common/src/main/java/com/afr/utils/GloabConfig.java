package com.afr.utils;

import org.springframework.stereotype.Service;

/**
 * @author Yan liang
 * @create 2019/4/2
 * @since 1.0.0
 */
@Service
public class GloabConfig {
    private String uploadFilePath;

    private String uploadImagePath;

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getUploadImagePath() {
        return uploadImagePath;
    }

    public void setUploadImagePath(String uploadImagePath) {
        this.uploadImagePath = uploadImagePath;
    }
}