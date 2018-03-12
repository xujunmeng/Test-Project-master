package 第一个实例;

import com.aliyun.openservices.ons.api.*;

import java.util.Properties;

/**
 * Created by james on 2017/5/23.
 */
public class ConsumerTest {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ConsumerId, "CID_junmeng_1");
        properties.put(PropertyKeyConst.AccessKey, "LTAIglSpYNV3EvoA");
        properties.put(PropertyKeyConst.SecretKey, "O0K1Lcs4DEnGHtzDtZc3kLUKDD8w7x");
        Consumer consumer = ONSFactory.createConsumer(properties);
        //final String topic, final String subExpression, final MessageListener listener
        consumer.subscribe("test_topic_junmeng_1", "*", new MessageListener() {
            public Action consume(Message message, ConsumeContext context) {
                System.out.println("Receive message : " + message);
                return Action.CommitMessage;
            }
        });
        consumer.start();
        System.out.println("Consumer Started");

    }

}
