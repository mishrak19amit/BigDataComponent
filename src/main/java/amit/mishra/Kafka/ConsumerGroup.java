package amit.mishra.Kafka;
import java.util.Properties;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerGroup {
   public static void main(String[] args) throws Exception {
      
      String topic = "TwitterHashTag";
      //TargetedVoiceTraffic TwitterHashTag 192.168.129.135:6667,192.168.129.90:6667,192.168.129.250:6667 192.168.129.234:6667,192.168.129.54:6667,192.168.129.151:6667
      String group = "group1";//args[1].toString();
      Properties props = new Properties();
      props.put("bootstrap.servers", "192.168.129.135:6667,192.168.129.90:6667,192.168.129.250:6667");
      props.put("group.id", group);
      props.put("enable.auto.commit", "true");
      props.put("auto.commit.interval.ms", "1000");
      props.put("session.timeout.ms", "30000");
      props.put("key.deserializer",          
         "org.apache.kafka.common.serialization.StringDeserializer");
      props.put("value.deserializer", 
         "org.apache.kafka.common.serialization.StringDeserializer");
      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
      
      consumer.subscribe(Arrays.asList(topic));
      System.out.println("Subscribed to topic " + topic);
      int i = 0;
      
      PrintWriter pr= new PrintWriter(new File("D:\\SparkOutput\\kafkamessagevoice.txt"));
      
      
      while (true) {
         ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
            {
            	
            	//System.out.println(String.valueOf(record.offset()) + ": " + new String(bytes, "UTF-8"));

         
            	 System.out.printf("["+i+"]: "+ record.value());
                 pr.println("["+i+"]: "+ record.value());
                 i++;
                 
                 //completMessage += new String(bytes, "UTF-8")+"\n";
            }
         pr.flush();     
      }     
   }  
}