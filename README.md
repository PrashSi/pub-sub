# Publish Subscriber System 
Distributed Systems Project 2: Simulate pub/sub using the filter based routing algorithm.

Publish Subscribe system was created using using filter based routing algorithm. In this example I have used 3 nodes or docker images that act as the brokers and relay message between netork of brokers. Each broker maintain its own routing table and keeps the list of subscription. There are total of 6 predefined publishers which publishes messages on 2 topics in a scheduled interval. 

The user interface is created using a java based UI framework Vaadin. The UI is build only to see the messages published on a particular topic. For this example the topics have been kept affix and so is the subscription. Every node has 2 subscribers which subscribe to local node when application boots.

## Steps to run the application.
To run the application use the following commands. 

Important:
*You should have MAVEN configured in your system in order to run the application.*
*You should also replace extra-host with your system IP in docker-compose otherwise it will not work.*

Step 1: cd ../pub-sub<br />
Step 2: mvn install // This should create a excutable JAR in the ../pub-sub/target folder<br />
Step 3: docker build -t pub-sub<br />
Step 4: docker-compose up<br />

Hit localhost:8000 in browser. This will open Dashboard for node 1 by default. You can navigate to different node's Dashboard by links provided.

Use Subscribe to subscribe to topic.
Use Refresh to see the messages in published and received message.



