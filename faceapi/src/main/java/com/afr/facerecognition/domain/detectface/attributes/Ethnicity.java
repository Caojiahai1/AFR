package com.afr.facerecognition.domain.detectface.attributes;

import com.afr.facerecognition.domain.detectface.enums.EthnicityEnum;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Ethnicity {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = EthnicityEnum.getEthnicityValue(value);
    }
}