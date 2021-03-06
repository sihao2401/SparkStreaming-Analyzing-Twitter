import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf}
import org.apache.spark.streaming.twitter._
object TwitterAnalyzer {
  def main(args: Array[String]): Unit = {

    import SentimentAnalyzer._

//    val rootLogger = Logger.getRootLogger()
//    rootLogger.setLevel(Level.ERROR)

    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)
    val filters = args.takeRight(args.length - 4)

    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    val sparkConf = new SparkConf().setAppName("TwitterAnalyzer").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    val stream = TwitterUtils.createStream(ssc, None,filters).filter(x=>x.getLang=="en")

    stream.foreachRDD(rdd=>{
      rdd.cache()
      val serializer = "org.apache.kafka.common.serialization.StringSerializer"
      val props = new Properties()
      props.put("bootstrap.servers", "localhost:9092")
      props.put("key.serializer", serializer)
      props.put("value.serializer", serializer)
      val producer = new KafkaProducer[String,String](props)
      rdd.collect().toList.foreach(tweet => {
        val txt = tweet.getText()
        producer.send(new ProducerRecord[String,String]("test",txt,mainSentiment(txt).toString()))
      })
      producer.close()
    })
    ssc.start()
    ssc.awaitTermination()
  }
}
