package com.afr.facerecognition.domain.detectface.attributes;

/**
 * @author Yan liang
 * @create 2019/4/5
 * @since 1.0.0
 */
public class Smile {
    //值为一个 [0,100] 的浮点数，小数点后3位有效数字。数值越大表示笑程度高。
    private double value;
    //代表笑容的阈值，超过该阈值认为有笑容。
    private int threshold;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}