package com.afr.facerecognition.domain.detectface;

/**
 * @author Yan liang
 * @create 2019/3/29
 * @since 1.0.0
 */
public class FaceRectangle {
    private int width;
    private int top;
    private int left;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}