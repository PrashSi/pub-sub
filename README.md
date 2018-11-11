# pub-sub
Distributed Systems-Project 2: Simulate pub/sub using a centralized pub/sub system.



## Run
To run the application use the following commands. 

**You should have MAVEN configured in your system in order to run the application.

cd ../pub-sub

mvn install

docker build -t pub-sub .

docker run -p 8000:8000 pub-sub

hit localhost:8000 in browser.

Use Refresh to see the messages in published and received message.


This application communicates through REST. There are 2 Topics and 2 Subscribers listening on each topic. We have also printed the activity on the console.
