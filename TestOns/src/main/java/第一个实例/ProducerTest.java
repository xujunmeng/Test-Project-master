package 第一个实例;

import com.aliyun.openservices.ons.api.*;

import java.util.Properties;

/**
 * Created by james on 2017/5/23.
 */
public class ProducerTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ProducerId, "PID_junmeng_2");
        properties.put(PropertyKeyConst.AccessKey, "null");
        properties.put(PropertyKeyConst.SecretKey, "null");
        Producer producer = ONSFactory.createProducer(properties);
        //在发送消息前，必须调用start方法来启动Producer,只需调用一次即可
        producer.start();
        //String topic, String tags, byte[] body
        Message msg = new Message("test_topic_junmeng_2", "TagA", "ORDERID_100", "Hello MQ".getBytes());
        SendResult sendResult = producer.send(msg);
        System.out.println("Send Message success. Message ID is: " + sendResult.getMessageId());

        //如果不销毁也没有问题
        producer.shutdown();
    }



}
