# Spring Boot Application
Spring Boot Application with implementation using Gradle build and H2 Database as repository.

A basic and simple Spring Boot service to handle customer transaction. 
The application follows a standard MVC architecture. It has controllers (TransactionController class) and models (Transaction class).

Prerequisites
1) Git
2) Gradle (To build in source doe locally)
3) Docker (Download for Docker desktop for windows or Mac)

## Run Application Using Docker file
1) Open terminal and change directory to location of apitest
2) Run `docker build -t santanderapp .` to build Docker image of the service named santanderapp exposing 8080.
3) After a successful image build run `docker run -it --rm --name santanderapp_service -p 8080:8080 santanderapp
   ` to spin up container to run the service in interactive mode mapping 8080 host to 8080 in container
4) After successful service would be reachable on port http://localhost:8080 

## Run Application Using Docker Compose
1) Open terminal and change directory to location of apitest
2) Run `docker compose up` this would build the image using the Dockerfile and spin up a container running the application on port 8080

## Endpoints
GET http://localhost:8080/api/transactions/{senderid}
GET http://localhost:8080/api/transactions/
POST http://localhost:8080/api/transactions/

## Sample Test with Curl
1) `curl --location 'http://localhost:8080/api/transactions'` get all transactions
2) `curl --location 'http://localhost:8080/api/transactions/se2@gmail.com'` get all transaction by a sender
3) `curl --location 'http://localhost:8080/api/transaction' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "sender":"sender2@gmail.com",
   "receiver":"reciever1@gmail.com",
   "amount":"0.2"
   }'`  execute a transaction 

## Alternative Test with postman
Import "santander.postman_collection.json" file into postman to test service endpoint via postman

Clone the repository:
git clone https://github.com/Ridwyn/apitest.git
