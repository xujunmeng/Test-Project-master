package 第一个案例;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;

/**
 * @author junmeng.xu
 * @date 2016年10月10日下午2:34:32
 */
public class Main {

	static AmazonS3 s3;
	static TransferManager tx;

	private static String AWS_ACCESS_KEY = "AKIAP5MNIPRMYBGODL4Q";
	private static String AWS_SECRET_KEY = "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";
	static final String bucketName = "cn.com.chinascope.jd";

	private static void init_with_key() throws Exception {
		AWSCredentials credentials = null;
		credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
		s3 = new AmazonS3Client(credentials);
		// Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		// s3.setRegion(usWest2);
		tx = new TransferManager(s3);
	}

	private static void listBuckets() {
        System.out.println("Listing buckets");
        int bucketNum = 0;
        for(Bucket bucket : s3.listBuckets()) {
            System.out.println(" - " + bucket.getName());
            bucketNum ++;
        }
        System.out.println("total " + bucketNum + " bucket(s).");
    }
	
	private static void listObjects(String bucketName) {
		System.out.println("Listing objects of " + bucketName);
		ObjectListing objectListing = s3.listObjects(bucketName);
		int objectNum = 0;
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			System.out.println(" - " + objectSummary.getKey());
			objectNum++;
		}
		System.out.println("total " + objectNum + " object(s).");
	}

	public static void main(String[] args) throws Exception {

		init_with_key();

		listObjects(bucketName);
	}

}
