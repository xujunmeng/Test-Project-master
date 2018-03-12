package 第二个案例;

import org.jets3t.service.Constants;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by junmeng.xu on 2016/12/28.
 */
public class Main {

    private static String AWS_ACCESS_KEY = "AKIAO6QTUB2BB6Y2GOJQ";
    private static String AWS_SECRET_KEY = "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";
    private static String bucketName = "cn.com.chinascope.jd";


    public static void main(String[] args) throws S3ServiceException {

        AWSCredentials awsCredentials = new AWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

        RestS3Service s3Service = new RestS3Service(awsCredentials, Constants.TYPE.S3CN);

        String key = "announce/fund/20161228/10a9da89677711739d1b4de6e01f0a32.pdf";

        ByteArrayOutputStream bos = null;

        try {
            S3Object fileObj = s3Service.getObject(bucketName, key);
            InputStream dataInputStream = fileObj.getDataInputStream();
            bos = new ByteArrayOutputStream(1024);

            byte[] temp = new byte[1024];
            int len;
            while ((len = dataInputStream.read(temp)) != -1) {
                bos.write(temp, 0, len);
            }

            byte[] bytes = bos.toByteArray();

            String s = bytes.toString();

            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
