package amit.mishra.Redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisMaster_slave_Connection {

	public static void main(String[] args) {
		//192.168.129.232:6379,192.168.129.149:6379,192.168.129.233:6379,192.168.129.234:6379,192.168.129.54:6379,192.168.129.151:6379
		String JEDISCLUSTER="192.168.129.232,192.168.129.149,192.168.129.233,192.168.129.234,192.168.129.54,192.168.129.151";
		String JEDISPORT="6379";
		String nodeIp[]=JEDISCLUSTER.split(",");
		Set<HostAndPort> connectionPoints = new HashSet<HostAndPort>();
		for (String string : nodeIp) {
			connectionPoints.add(new HostAndPort(string, Integer.parseInt(JEDISPORT)));
		}
		JedisCluster jedisClusterconnection= new JedisCluster(connectionPoints);
		try {
			jedisClusterconnection.append("Connection", "Check");
			jedisClusterconnection.del("Connection");
			System.out.println("Able to connect to Redis");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to Redis");
			// e.printStackTrace();
		}
		finally {
			try {
				jedisClusterconnection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
