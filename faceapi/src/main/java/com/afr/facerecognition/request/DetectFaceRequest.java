package com.afr.facerecognition.request;

import com.afr.utils.filehelper.FileHelper;
import com.afr.utils.stringhelper.StringHelper;
import com.afr.facerecognition.base.BaseFaceRequest;
import com.afr.facerecognition.domain.FaceAttributesEnum;
import com.afr.facerecognition.response.DetectFaceResponse;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public class DetectFaceRequest extends BaseFaceRequest<DetectFaceResponse> {
    // 图片的 URL。
    private String imageUrl;

    // 一个图片，二进制文件，需要用post multipart/form-data的方式上传。
    private File imageFile;

    // base64 编码的二进制图片数据 如果同时传入了 image_url、image_file 和 image_base64 参数，本API使用顺序为 image_file 优先，image_url 最低。
    private String imageBase64;

    /*
    * 是否检测并返回人脸关键点
    * 2 检测。返回 106 个人脸关键点。
    * 1	检测。返回 83 个人脸关键点。
    * 0	不检测
    * 注：本参数默认值为 0
    * */
    private int returnLandmark;

    /*
    * 是否检测并返回根据人脸特征判断出的年龄、性别、情绪等属性。
    * 需要将属性组成一个用逗号分隔的字符串，属性之间的顺序没有要求。
    * 属性值使用枚举类FaceAttributesEumn
    * */
    private List<FaceAttributesEnum> returnAttributes;

    // 是否检测并返回所有人脸的人脸关键点和人脸属性（仅正式 API Key 可以使用）
    private int calculateAll;

    // 是否指定人脸框位置进行人脸检测。（仅正式 API Key 可以使用）
    private String faceRectangle;

    /*
    * 颜值评分分数区间的最小值。默认为0
    * 注：默认颜值评分分数区间为0-100.可通过beauty_score_min和beauty_score_max来调节分数区间，满足您的场景需求
    * */
    private int beautyScoreMin;

    // 颜值评分分数区间的最大值。默认为100
    private int beautyScoreMax;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public int getReturnLandmark() {
        return returnLandmark;
    }

    public void setReturnLandmark(int returnLandmark) {
        this.returnLandmark = returnLandmark;
    }

    public List<FaceAttributesEnum> getReturnAttributes() {
        return returnAttributes;
    }

    public void setReturnAttributes(List<FaceAttributesEnum> returnAttributes) {
        this.returnAttributes = returnAttributes;
    }

    public int getCalculateAll() {
        return calculateAll;
    }

    public void setCalculateAll(int calculateAll) {
        this.calculateAll = calculateAll;
    }

    public String getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(String faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public int getBeautyScoreMin() {
        return beautyScoreMin;
    }

    public void setBeautyScoreMin(int beautyScoreMin) {
        this.beautyScoreMin = beautyScoreMin;
    }

    public int getBeautyScoreMax() {
        return beautyScoreMax;
    }

    public void setBeautyScoreMax(int beautyScoreMax) {
        this.beautyScoreMax = beautyScoreMax;
    }

    public static void setApiUrl(String apiUrl) {
        DetectFaceRequest.apiUrl = apiUrl;
    }

    // api地址
    private static String apiUrl = "https://api-cn.faceplusplus.com/facepp/v3/detect";

    @Override
    protected String getApiUrl() {
        return apiUrl;
    }

    @Override
    protected Map<String, String> addApiParams() throws Exception{
        Map<String, String> paramsMap = new HashMap<>();
        String errMsg = checkParam();
        if (!StringHelper.IsNullOrEmpty(errMsg)) {
            throw new Exception(errMsg);
        }
        if (!StringHelper.IsNullOrEmpty(getImageUrl())) {
            paramsMap.put("image_url", getImageUrl());
        }
        if (!StringHelper.IsNullOrEmpty(getImageBase64())) {
            paramsMap.put("image_base64", getImageBase64());
        }
        if (getReturnLandmark() == 1) {
            paramsMap.put("return_landmark", "1");
        }
        if (getBeautyScoreMin() > 0) {
            paramsMap.put("beauty_score_min", StringHelper.ToString(getBeautyScoreMin()));
        }
        if (getBeautyScoreMax() > 0) {
            paramsMap.put("beauty_score_max", StringHelper.ToString(getBeautyScoreMax()));
        }
        List<FaceAttributesEnum> returnAttributesList = getReturnAttributes();
        if (returnAttributesList != null && returnAttributesList.size() > 0) {
            paramsMap.put("return_attributes", StringHelper.listToString(returnAttributesList, ","));
        }
        return paramsMap;
    }

    @Override
    protected Map<String, byte[]> addApiByteParams(){
        Map<String, byte[]> byteMap = new HashMap<>();
        if (getImageFile() != null) {
            byteMap.put("image_file", FileHelper.getBytesFromFile(getImageFile()));
        }
        return byteMap;
    }

    @Override
    protected Class<DetectFaceResponse> getResponseClass() {
        return DetectFaceResponse.class;
    }

    /**
     *@description 检查必传参数
     *@params  []
     *@return  java.lang.String
     *@creater  yanliang
     *@createdate  2019/3/29
     *@info
     */
    private String checkParam() {
        if (getImageFile() == null && StringHelper.IsNullOrEmpty(getImageBase64()) && StringHelper.IsNullOrEmpty(getImageUrl())) {
            return "请上传需要识别的图片！";
        }
        return null;
    }
}