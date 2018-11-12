# pub-sub  filtered based routing
Distributed Systems-Project 2: Simulate pub/sub using a centralized pub/sub system.



## Run
To run the application use the following commands. 

**You should have MAVEN configured in your system in order to run the application.

**You should also replace extra-host with your system IP otherwise it will not work.

STEP 1: cd ../pub-sub

STEP 2: mvn install
// This should create a excutable JAR in the ../pub-sub/target folder.

STEP 3: docker build -t pub-sub .

STEP 4: docker-compose up


Hit localhost:8000 in browser. This will open Dashboard for node 1 by default. You can navigate to different node's Dashboard by links provided.

Use Subscribe to subscribe to topic.

Use Refresh to see the messages in published and received message.



