package com.afr.entrys.businessdomain;

/**
 * @author Yan liang
 * @create 2019/4/2
 * @since 1.0.0
 */
public class DetectFaceRequestParam {

    private String localPath;

    private String netUrl;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    @Override
    public String toString() {
        return "DetectFaceRequestParam{" +
                "localPath='" + (localPath == null ? "" : localPath) + '\'' +
                ",netUrl='" + (netUrl == null ? "" : netUrl) + '\'' +
                '}';
    }
}