package amit.mishra.RabbitMQ;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RecieveLog {
	private static final String EXCHANGE_NAME="RABBITMQ_LOGS";
	public static void main(String[] args) {
		ConnectionFactory factory= new ConnectionFactory();
		factory.setHost("192.168.129.101");
		factory.setUsername("clearinsight");
		factory.setPassword("clearinsight");
		try {
			Connection connection = factory.newConnection();
			Channel channel= connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			channel.queueBind("RABBITMQ_QUEUE", EXCHANGE_NAME, "");
			channel.queueBind("RABBITMQ_QUEUE1", EXCHANGE_NAME, "");
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			
			Consumer consumer= new DefaultConsumer(channel) {
				public void handleDelivery(String consumerTag, Envelope envelope,
                        AMQP.BasicProperties properties, byte[] body)
				{
					try {
						String message= new String(body,"UTF-8");
						System.out.println(" [x] Received '" + message + "'");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			channel.basicConsume("RABBITMQ_QUEUE",true, consumer);
			channel.basicConsume("RABBITMQ_QUEUE1",true, consumer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
