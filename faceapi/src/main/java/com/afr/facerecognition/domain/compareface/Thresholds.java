package com.afr.facerecognition.domain.compareface;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yan liang
 * @create 2019/4/9
 * @since 1.0.0
 */
public class Thresholds {
    @JsonProperty("1e-3")
    private float e3;

    @JsonProperty("1e-4")
    private float e4;

    @JsonProperty("1e-5")
    private float e5;

    public float getE3() {
        return e3;
    }

    public void setE3(float e3) {
        this.e3 = e3;
    }

    public float getE4() {
        return e4;
    }

    public void setE4(float e4) {
        this.e4 = e4;
    }

    public float getE5() {
        return e5;
    }

    public void setE5(float e5) {
        this.e5 = e5;
    }
}