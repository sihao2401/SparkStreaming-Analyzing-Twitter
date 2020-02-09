# Spark Streaming with Twitter and Kafka

### Running Environment

java version  "1.8.0_181"

sbt version      1.3.2

kafka version   2.3.0



## Instruction

- These files don't have jar file, you should go to root of your project and build by the following commands:

  ```powershell
  > sbt assembly
  ```

This will create a fat jar under target/scala directory.



- Go to root of Kafka and start a Zookeeper server and Kafka server

  ```powershell
  > bin/zookeeper-server-start.sh config/zookeeper.properties	
  > bin/kafka-server-start.sh config/server.properties
  ```

  create a topic named "test" with a single partition and only one replica

  ```powershell
  > bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
  ```

  start a consumer

  ```powershell
  > bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
  ```

  

- Run the Spark project by using the following command:

  ```powershell
  > spark-submit --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.0 --class <ClassName> <PathToJarFile> <consumerKey> <consumerSecret> <accessToken> <accessTokenSecret> <Topic words you chose> 
  ```



- Run Elasticsearch and Kibana:

  ```powershell
  > ./elasticsearch
  > ./kibana
  ```



- Go to the logstash directory and create a ﬁle logstash-simple.conf with following content:

  ```
  input { 
  kafka { 
  bootstrap_servers => "localhost:9092" 
  topics => ["YourTopic"] 
  } 
  }
  
  output { 
  elasticsearch { 
  hosts => ["localhost:9200"] 
  index => "YourTopic-index" 
  } 
  }
  ```

  Then run the command

  ```powershell
  > bin/logstash -f logstash-simple.conf
  ```

  

- You can go to http://localhost:5601 and use Kibana to visualize your data in real-time
