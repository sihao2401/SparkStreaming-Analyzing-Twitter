name := "SparkStreamingTwitter"

version := "0.1"

scalaVersion := "2.11.12"

lazy val root = (project in file(".")).
  settings(
    name := "SparkStreamingTwitter",
    version := "1.0",
    scalaVersion := "2.11.12",
    mainClass in Compile := Some("TwitterAnalyzer")
  )

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"% "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4"% "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4"% "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.4"% "provided"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.4"% "provided"
libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.0"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.4"
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.4"

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp"))

//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4" % "compile"
//libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4" % "compile"
//libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4" % "compile"
//libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.4.4" % "compile"
//libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.4" % "compile"
//libraryDependencies += "org.apache.bahir" %% "spark-streaming-twitter" % "2.4.0"
//libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.0"
//
//libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.4"
//libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.4"



