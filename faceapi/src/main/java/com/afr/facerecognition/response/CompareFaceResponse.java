package com.afr.facerecognition.response;

import com.afr.facerecognition.base.BaseFaceResponse;
import com.afr.facerecognition.domain.compareface.Thresholds;
import com.afr.facerecognition.domain.detectface.Faces;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/4/9
 * @since 1.0.0
 */
public class CompareFaceResponse extends BaseFaceResponse {

    @JsonProperty("confidence")
    private float confidence;

    @JsonProperty("thresholds")
    private Thresholds thresholds;

    @JsonProperty("image_id1")
    private String imageId1;

    @JsonProperty("image_id2")
    private String imageId2;

    @JsonProperty("faces1")
    private List<Faces> faces1;

    @JsonProperty("faces2")
    private List<Faces> faces2;

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public Thresholds getThresholds() {
        return thresholds;
    }

    public void setThresholds(Thresholds thresholds) {
        this.thresholds = thresholds;
    }

    public String getImageId1() {
        return imageId1;
    }

    public void setImageId1(String imageId1) {
        this.imageId1 = imageId1;
    }

    public String getImageId2() {
        return imageId2;
    }

    public void setImageId2(String imageId2) {
        this.imageId2 = imageId2;
    }

    public List<Faces> getFaces1() {
        return faces1;
    }

    public void setFaces1(List<Faces> faces1) {
        this.faces1 = faces1;
    }

    public List<Faces> getFaces2() {
        return faces2;
    }

    public void setFaces2(List<Faces> faces2) {
        this.faces2 = faces2;
    }
}