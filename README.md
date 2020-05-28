# springboot-kafka-docker

1. Spring Boot
2. Java 11
3. Kafka (Producer and Consumer)
4. Exposed POST api via https
4. Runs on Docker


#### Steps to install Kafka in local
```
1. Download from http://kafka.apache.org/downloads 
2. Version I have dowloaded : kafka_2.12-2.4.1.tgz
3. Unzip the tgz file under C: Drive (To avoid long path issue)

Please do the following changes once you Unzip tgz file
1. Set an environment path for : C:\kafka_2.12\bin\windows (to run the below commands in local)

2. Create a folder called "kafka-logs" to store our kafka logs Ex: C:\kafka_2.12\kafka-logs

3. Create a folder called "zookeeper_data" to store zookeeper data Ex: C:\kafka_2.12\zookeeper_data

4. Open C:\kafka_2.12\config\server.properties file and add or update below attributres
    log.dirs=C:\kafka_2.12\kafka-logs
    offsets.topic.num.partitions=1
    offsets.topic.replication.factor=1
    transaction.state.log.replication.factor=1
    transaction.state.log.min.isr=1
    min.insync.replicas=1
    default.replication.factor=1

5. Open C:\kafka_2.12\config\zookeeper.properties file and set "dataDir" as below
    dataDir=C:\kafka_2.12\zookeeper_data
```

#### Run Kafka in Local using below commands
```
1. Open Git bash and start the Kafka Zookeeper
    $ zookeeper-server-start.bat /c/kafka_2.12/config/zookeeper.properties

2. Open another Git bash and start the Kafka Server
    $ kafka-server-start.bat /c/kafka_2.12/config/server.properties

3. Open another Git bash Create Kafka Topic
    $ kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-topic 
```


#### Sample Request
Start Kafka Zookeeper and Kafka Server before starting the application springboot-kafka-docker
```$xslt
Request:
    Method  : POST
    Url     : https://localhost:8082/employee
    body    : 
        {
            "empNo" : "1234",
            "empName" : "Mahesh"
        }
```


#### Sample Log
```$xslt
2020-05-28 22:58:48.374  INFO 9192 --- [nio-8082-exec-6] c.l.k.s.service.EmployeeService          : About to publish employee {
  "empNo" : "1234",
  "empName" : "Mahesh"
}
2020-05-28 22:58:48.375  INFO 9192 --- [nio-8082-exec-6] c.l.k.s.producer.EmployeeProducer        : Message Published
2020-05-28 22:58:48.384  INFO 9192 --- [ntainer#0-0-C-1] c.l.k.s.consumer.EmployeeConsumer        : Message received from topic, body => {"empNo":"1234","empName":"Mahesh"} 
2020-05-28 22:58:48.384  INFO 9192 --- [ntainer#0-0-C-1] c.l.k.s.consumer.EmployeeConsumer        : Kafka Header CorrelationId : ce7eb936-5345-476f-8d1c-bb7dcc3a5981
```