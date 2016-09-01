java -cp KafkaOffsetMonitor-assembly-0.3.0-SNAPSHOT.jar com.quantifind.kafka.offsetapp.OffsetGetterWeb --zk localhost:2181 --port 22181 --refresh 5.minutes --retain 1.day > ./monitor.log
