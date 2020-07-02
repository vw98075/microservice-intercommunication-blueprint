# microservice-intercommunication-blueprint

## Intercommunication Types

### Gateway To Regular Node

Rerouting with RESTFul 

### One To One (Between Nodes)

RSocket (request-and-response, request-and-forget, request-and-stream, channel)

### Broadcasting And Subscribing (One To Many)

Kafka (stream-processing software platform)

## Running Microservices

To run this set of microservices, you need to have Java 14 (or 11, 8, but changes are needed in those pom files accordingly). And you also need to have Kafka running in the same box. After bringing up all four servers, you can run the following command

curl http://localhost:8080/orders;echo;




