package Hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseConnection {

	public static void main(String[] args) {
		Configuration conf = HBaseConfiguration.create();
		 conf.set("hbase.zookeeper.property.clientPort", "2181");
		 conf.set("hbase.zookeeper.quorum", "bihdp01,bihdp02,bihdp03");
		 conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		 Connection conn;
		try {
			conn = ConnectionFactory.createConnection(conf);
			Admin admin = conn.getAdmin();
			 
			 if (!admin.tableExists(TableName.valueOf("Htable"))) {
			     admin.createTable(new HTableDescriptor(TableName.valueOf("Htable")).addFamily(new HColumnDescriptor("cf")));
			 }
			 Table table = conn.getTable(TableName.valueOf("Htable"));
			 System.out.println(table.getName());
			 Put p = new Put(Bytes.toBytes("AAPL10232015"));
			 p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("close"), Bytes.toBytes(119));
			 table.put(p);
			 p = new Put(Bytes.toBytes("AAPL10232015Amit"));
			 p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("close"), Bytes.toBytes(120));
			 table.put(p);
			 Result r = table.get(new Get(Bytes.toBytes("AAPL10232015")));
			 System.out.println(r);
			 r = table.get(new Get(Bytes.toBytes("AAPL10232015Amit")));
			 System.out.println(r);
			 /*admin.disableTable(TableName.valueOf("Amit"));
			 admin.disableTable(TableName.valueOf("Amit1"));
			 admin.deleteTable(TableName.valueOf("Amit"));
			 admin.deleteTable(TableName.valueOf("Amit1"));
			 TableName[] tm=admin.listTableNames();
			 for (TableName tableName : tm) {
				System.out.println(tableName.getNameAsString());
			}*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
}
