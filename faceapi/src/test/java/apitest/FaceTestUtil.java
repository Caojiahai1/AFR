package apitest;

import com.afr.facerecognition.request.DetectFaceRequest;
import org.junit.Test;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public class FaceTestUtil {
    @Test
    public void testDetetFace() {
        DetectFaceRequest request = new DetectFaceRequest();
        request.setImageUrl("https://cdn.faceplusplus.com.cn/mc-official/scripts/demoScript/images/demo-pic6.jpg");
        request.setReturnLandmark(1);
        request.getResponse();
    }
}