package 第一个例子;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

/**
 * @author junmeng.xu
 * @date 2016年7月11日下午3:31:26
 */
public class kafkaProducer extends Thread {

	private String topic;

	public kafkaProducer(String topic) {

		this.topic = topic;

	}

	@Override
	public void run() {

		Producer producer = createProducer();

		int i = 0;

		while (true) {

			producer.send(new KeyedMessage<Integer, String>(topic, "message: "
					+ i));

			System.out.println("发送了: " + i);

			try {

				TimeUnit.SECONDS.sleep(1);

				i++;

			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		}

	}

	private Producer createProducer() {

		Properties properties = new Properties();

		properties.put("zookeeper.connect",
				"192.168.100.35:2181");// 声明zk

		properties.put("serializer.class", StringEncoder.class.getName());

		properties.put("metadata.broker.list",
				"192.168.100.35:9092");// 声明kafka
																			// broker

		return new Producer<Integer, String>(new ProducerConfig(properties));

	}

	public static void main(String[] args) {

		new kafkaProducer("test").start();// 使用kafka集群中创建好的主题 test

	}

}
