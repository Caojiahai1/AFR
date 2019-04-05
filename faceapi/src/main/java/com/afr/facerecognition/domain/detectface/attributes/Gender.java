package com.afr.facerecognition.domain.detectface.attributes;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Gender {
    /*性别分析结果。返回值为：
    Male	男性
    Female	女性*/
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}