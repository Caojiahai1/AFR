package com.afr.entrys.businessdomain;

/**
 * @author Yan liang
 * @create 2019/4/10
 * @since 1.0.0
 */
public class CompareFaceRequestParam {
    private String faceToken1;

    private String faceToken2;

    private String netUrl1;

    private String netUrl2;

    private String localPath1;

    private String localPath2;

    public String getFaceToken1() {
        return faceToken1;
    }

    public void setFaceToken1(String faceToken1) {
        this.faceToken1 = faceToken1;
    }

    public String getFaceToken2() {
        return faceToken2;
    }

    public void setFaceToken2(String faceToken2) {
        this.faceToken2 = faceToken2;
    }

    public String getNetUrl1() {
        return netUrl1;
    }

    public void setNetUrl1(String netUrl1) {
        this.netUrl1 = netUrl1;
    }

    public String getNetUrl2() {
        return netUrl2;
    }

    public void setNetUrl2(String netUrl2) {
        this.netUrl2 = netUrl2;
    }

    public String getLocalPath1() {
        return localPath1;
    }

    public void setLocalPath1(String localPath1) {
        this.localPath1 = localPath1;
    }

    public String getLocalPath2() {
        return localPath2;
    }

    public void setLocalPath2(String localPath2) {
        this.localPath2 = localPath2;
    }
}