# microservice-intercommunication-blueprint

## Intercommunication Types

### Gateway To Regular Node

Rerouting with RESTFul API 

### One To One 

RSocket (request-response, fire-and-forget, request-stream, channel)

### One To Many 

Kafka (broadcast And subscription - stream-processing software platform)

## Running Microservices

To run this set of four microservices, you need to have Java 14 (or 11, 8, but changes are needed in those pom files accordingly). And you also need to have Kafka running in the same box. After bringing up all four servers, you can run the following command
```
curl http://localhost:8080/orders;echo;
```


