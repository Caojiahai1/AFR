package com.afr.facerecognition.domain.detectface.attributes;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Age {

    //年龄分析结果。返回值为一个非负整数。
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}