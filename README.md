# Microservice Communication Blueprint For 2020 And Beyond

## Communication Types

This set of microservices is designed to demonstrate communication methods between microservices.

### 1. Gateway To Regular Node

Rerouting with RESTFul API 

### 2. Node To Node (Multiplexed) 

RSocket (request-response, fire-and-forget, request-stream, channel) *

### 3. Node To Nodes 

Broadcast and subscription communication model - stream-processing software platform such as Kafka

## Run The applications

To run this set of four microservices, you need to have Java 14 (or 11, 8, but some changes are needed in those pom files accordingly). And you also need to have Kafka installed. 

#### 1. Start Up Kafka

In the Kafka home directory, run 
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
on one terminal and
```
bin/kafka-server-start.sh config/server.properties
```
on another terminal.

#### 2. Start Up the Other Four Servers

```
mvn package
java -jar target/<server jar file>
```

#### 3. Start Up Zipkin

```
java -jar zipkin.jar
```
where zipkin.jar can be downloaded from its office website.

At this point, you can run the following command on a terminal

```
curl http://localhost:8080/orders;echo;
```
Flow: 

gateway(reroute) --> payment (request & response) --> fulfillment 

............ payment (source) --> Kafka --> (sink) customer satisfaction

To see a distributed tracing information, point your browser to http://localhost:9411/zipkin/. At this moment (Sept. 20, 2020). Spring Cloud Sleuth doesn't support RSocket. As a result, the communication from payment to fulfillment doesn't show at Zipkin. This feature is in process.   

\* To have a better understanding on why a microservice node to node communication shall use RSocket instead of REST, please read an article on RSocket https://dzone.com/articles/rsocket-in-cloud-native .

