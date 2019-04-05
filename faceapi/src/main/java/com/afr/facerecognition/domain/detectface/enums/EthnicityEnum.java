package com.afr.facerecognition.domain.detectface.enums;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public enum EthnicityEnum {
    ASIAN("亚洲人"),
    WHITE("白人"),
    BLACK("黑人");

    private String ethnicity;

    EthnicityEnum(String ethnicity) {
        this.ethnicity = ethnicity;
    }
    public String getEthnicity() {
        return this.ethnicity;
    }

    public static String getEthnicityValue(String enumValue) {
        EthnicityEnum[] list= EthnicityEnum.values();
        for (EthnicityEnum type : list)
        {
            if (type.toString().equals(enumValue)) {
                return type.getEthnicity();
            }
        }
        return "";
    }
}