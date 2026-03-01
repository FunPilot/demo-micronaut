# demo-micronaut
Example application to demonstrate micronaut
- JDK21+
- JPA
- H2 database
- REST API
- Kafka

## Run
Run Application class of mktdata module  
Run Application class of signal module  (receive Kafka event)  

Kafka testing  
- assume Kafka is setup at localhost:9092 / or update properties kafka.bootstrap.servers  
- in module mktdata set properties mktdata.enable-kafka = true  

## REST endpoint for testing
http://localhost:9080/v1/price/last?symbol=NVDA&exchange=NYSE  

### Author
Eddie Wong