package com.afr.facerecognition.response;

import com.afr.facerecognition.base.BaseFaceResponse;
import com.afr.facerecognition.domain.detectface.Faces;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public class DetectFaceResponse extends BaseFaceResponse {
    // 被检测的图片在系统中的标识。
    @JsonProperty("image_id")
    private String imageId;
    @JsonProperty("faces")
    private List<Faces> faces;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<Faces> getFaces() {
        return faces;
    }

    public void setFaces(List<Faces> faces) {
        this.faces = faces;
    }
}