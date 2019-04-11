package com.afr.facerecognition.request;

import com.afr.facerecognition.base.BaseFaceRequest;
import com.afr.facerecognition.response.CompareFaceResponse;
import com.afr.utils.filehelper.FileHelper;
import com.afr.utils.stringhelper.StringHelper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/4/9
 * @since 1.0.0
 */
public class CompareFaceRequest extends BaseFaceRequest<CompareFaceResponse> {

    //第一个人脸标识 face_token，优先使用该参数
    @JsonProperty("face_token1")
    private String faceToken1;

    //第一张图片的 URL
    @JsonProperty("image_url1")
    private String imageUrl1;

    //第一张图片，二进制文件，需要用 post multipart/form-data 的方式上传。
    @JsonProperty("image_file1")
    private File imageFile1;

    //base64 编码的二进制图片数据
    //如果同时传入了 image_url1、image_file1 和 image_base64_1 参数，本 API 使用顺序为image_file1 优先，image_url1 最低。
    @JsonProperty("image_base64_1")
    private String imageBase641;

    //第二个人脸标识 face_token，优先使用该参数
    @JsonProperty("face_token2")
    private String faceToken2;

    //第二张图片的 URL
    @JsonProperty("image_url2")
    private String imageUrl2;

    //第二张图片，二进制文件，需要用 post multipart/form-data 的方式上传。
    @JsonProperty("image_file2")
    private File imageFile2;

    //base64 编码的二进制图片数据
    //如果同时传入了 image_url1、image_file1 和 image_base64_1 参数，本 API 使用顺序为image_file1 优先，image_url1 最低。
    @JsonProperty("image_base64_2")
    private String imageBase642;

    public String getFaceToken1() {
        return faceToken1;
    }

    public void setFaceToken1(String faceToken1) {
        this.faceToken1 = faceToken1;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public File getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(File imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public String getImageBase641() {
        return imageBase641;
    }

    public void setImageBase641(String imageBase641) {
        this.imageBase641 = imageBase641;
    }

    public String getFaceToken2() {
        return faceToken2;
    }

    public void setFaceToken2(String faceToken2) {
        this.faceToken2 = faceToken2;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public File getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(File imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public String getImageBase642() {
        return imageBase642;
    }

    public void setImageBase642(String imageBase642) {
        this.imageBase642 = imageBase642;
    }

    // api地址
    private static String apiUrl = "https://api-cn.faceplusplus.com/facepp/v3/compare";

    @Override
    protected String getApiUrl() {
        return apiUrl;
    }

    @Override
    protected Map<String, String> addApiParams() throws Exception {
        Map<String, String> paramsMap = new HashMap<>();
        String errMsg = checkParam();
        if (!StringHelper.IsNullOrEmpty(errMsg)) {
            throw new Exception(errMsg);
        }
        if (!StringHelper.IsNullOrEmpty(getFaceToken1())) {
            paramsMap.put("face_token1", getFaceToken1());
        }
        if (!StringHelper.IsNullOrEmpty(getImageUrl1())) {
            paramsMap.put("image_url1", getImageUrl1());
        }
        if (!StringHelper.IsNullOrEmpty(getImageBase641())) {
            paramsMap.put("image_base64_1", getImageBase641());
        }
        if (!StringHelper.IsNullOrEmpty(getFaceToken2())) {
            paramsMap.put("face_token2", getFaceToken2());
        }
        if (!StringHelper.IsNullOrEmpty(getImageUrl2())) {
            paramsMap.put("image_url2", getImageUrl2());
        }
        if (!StringHelper.IsNullOrEmpty(getImageBase642())) {
            paramsMap.put("image_base64_2", getImageBase642());
        }
        return paramsMap;
    }

    @Override
    protected Map<String, byte[]> addApiByteParams() {
        Map<String, byte[]> byteMap = new HashMap<>();
        if (getImageFile1() != null) {
            byteMap.put("image_file1", FileHelper.getBytesFromFile(getImageFile1()));
        }
        if (getImageFile2() != null) {
            byteMap.put("image_file2", FileHelper.getBytesFromFile(getImageFile2()));
        }
        return byteMap;
    }

    @Override
    protected Class<CompareFaceResponse> getResponseClass() {
        return CompareFaceResponse.class;
    }

    private String checkParam() {
        if (getImageFile1() == null && StringHelper.IsNullOrEmpty(getImageBase641())
                && StringHelper.IsNullOrEmpty(getImageUrl1()) && StringHelper.IsNullOrEmpty(getFaceToken1())) {
            return "请上传第一张图片所需参数！";
        }
        if (getImageFile2() == null && StringHelper.IsNullOrEmpty(getImageBase642())
                && StringHelper.IsNullOrEmpty(getImageUrl2()) && StringHelper.IsNullOrEmpty(getFaceToken2())) {
            return "请上传第二张图片所需参数！";
        }
        return null;
    }
}