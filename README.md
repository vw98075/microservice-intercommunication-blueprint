# Microservice Intercommunication Blueprint For 2020 And Beyond

## Intercommunication Types

### 1. Gateway To Regular Node

Rerouting with RESTFul API 

### 2. Node To Node (Multiplexed) 

RSocket (request-response, fire-and-forget, request-stream, channel) *

### 3. Node To Nodes 

Broadcast and subscription communication model - stream-processing software platform such as Kafka

## Run The applications

To run this set of four microservices, you need to have Java 14 (or 11, 8, but changes are needed in those pom files accordingly). And you also need to have Kafka running in the same box. After bringing up all four servers, you can run the following command
```
curl http://localhost:8080/orders;echo;
```
\* To have a better understanding on why a microservice node to node intercommunication shall use RSocket instead of REST, please read an article on RSocket https://dzone.com/articles/rsocket-in-cloud-native .

