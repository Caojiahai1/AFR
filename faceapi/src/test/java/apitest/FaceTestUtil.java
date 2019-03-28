package apitest;

import com.afr.faceRecognition.request.DetectFaceRequest;
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
        request.getResponse();
    }
}