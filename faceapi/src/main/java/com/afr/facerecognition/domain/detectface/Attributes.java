package com.afr.facerecognition.domain.detectface;

import com.afr.facerecognition.domain.detectface.attributes.*;

/**
 * @author Yan liang
 * @create 2019/3/29
 * @since 1.0.0
 */
public class Attributes {
    private Gender gender;

    private Age age;

    private Smile smile;

    private Ethnicity ethnicity;

    private Beauty beauty;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Smile getSmile() {
        return smile;
    }

    public void setSmile(Smile smile) {
        this.smile = smile;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Beauty getBeauty() {
        return beauty;
    }

    public void setBeauty(Beauty beauty) {
        this.beauty = beauty;
    }
}