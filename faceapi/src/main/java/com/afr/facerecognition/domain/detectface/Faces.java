package com.afr.facerecognition.domain.detectface;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yan liang
 * @create 2019/3/29
 * @since 1.0.0
 */
public class Faces {
    // 人脸的标识
    @JsonProperty("face_token")
    private String faceToken;

    /*
    * 人脸矩形框的位置，包括以下属性。每个属性的值都是整数：
    * 1、top：矩形框左上角像素点的纵坐标
    * 2、left：矩形框左上角像素点的横坐标
    * 3、width：矩形框的宽度
    * 4、height：矩形框的高度
    * */
    @JsonProperty("face_rectangle")
    private FaceRectangle faceRectangle;

    //人脸的关键点坐标数组。 todo
    @JsonProperty("landMark")
    private LandMark landMark;

    //人脸属性特征
    @JsonProperty("attributes")
    private Attributes attributes;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public FaceRectangle getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(FaceRectangle faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public LandMark getLandMark() {
        return landMark;
    }

    public void setLandMark(LandMark landMark) {
        this.landMark = landMark;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}