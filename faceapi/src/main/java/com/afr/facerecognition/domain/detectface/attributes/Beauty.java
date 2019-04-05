package com.afr.facerecognition.domain.detectface.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Beauty {

    @JsonProperty("female_score")
    private double femaleScore;
    @JsonProperty("male_score")
    private double maleScore;

    public double getFemaleScore() {
        return femaleScore;
    }

    public void setFemaleScore(double femaleScore) {
        this.femaleScore = femaleScore;
    }

    public double getMaleScore() {
        return maleScore;
    }

    public void setMaleScore(double maleScore) {
        this.maleScore = maleScore;
    }
}