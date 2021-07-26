# Restaurant API
This api allows retrieving restaurants detailed information. This API is based on Google API.
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [API Contract](#api-contract)
* [Setup](#setup)

## General info
This project is a Spring API that connects to Google Maps Restaurants API available at https://developers.google.com/maps/documentation/places/web-service/
It has two client GET requests.
Search request provides a general list of restaurants based on 4 criterias entered as parameters:
location, cuisine, radius & ratings:
### GET restaurants/search?location=${required_location}&cuisine=${optional}&radius=${radius}$rating={$rating}
Each restaurant has unique ID reference which can be used to obtain further details in another request:
### GET restaurants/${id}



## Public Endpoint
This Rest API has been deployed in AWS in an EC2 instance and using port 8081.

``
http://ec2-18-130-4-145.eu-west-2.compute.amazonaws.com:8081//restaurants/search?location=london
``

## Technologies/Main Libraries
Project is created with:

* Maven
* Java 1.11
* Spring Boot
* Json 
* Junit
* Gson version: 2.8.2
* Mockito
* AWS: EC2, and security groups

## API contracts:
### HTTP GET /restaurants/search?location=${required_location}&cuisine=${optional}&radius=${radius}$rating={$rating}
Get the restaurants for a city.
Example:
``
http://ec2-18-130-4-145.eu-west-2.compute.amazonaws.com:8081//restaurants/search?location=london
``

#### HTTP responses:
##### HTTP 200
```
[
  {
    "id": "ChIJn1DE_XsFdkgR6NyYk4IWd64",
    "name": "HIDE",
    "address": "85 Piccadilly, London W1J 7NB, United Kingdom",
    "rating": "4.6",
    "priceLevel": 4,
    "photo": "<a href=\"https://maps.google.com/maps/contrib/109739309313242938382\">Rog Edwards</a>"
  },
  {
    "id": "ChIJ2Qu8ycwEdkgRAGMpL0ZIvEs",
    "name": "Circus",
    "address": "27-29 Endell St, London WC2H 9BA, United Kingdom",
    "rating": "4.2",
    "priceLevel": 3,
    "photo": "<a href=\"https://maps.google.com/maps/contrib/104540222813005388870\">lucy Hughes</a>"
  },
  ...
] 
```
### GET restaurants/${id}
``
http://ec2-18-130-4-145.eu-west-2.compute.amazonaws.com:8081//restaurants/ChIJn1DE_XsFdkgR6NyYk4IWd64
``
#### HTTP responses:
##### HTTP 200

```
{
"id": "ChIJn1DE_XsFdkgR6NyYk4IWd64",
"address": "85,Piccadilly,London,Greater London,England,United Kingdom,W1J 7NB",
"rating": "4.6",
"priceLevel": 4,
"photo": "<a href=\"https://maps.google.com/maps/contrib/116553686160891867788\">HIDE</a>",
"website": "https://www.hide.co.uk/",
"userRatingTotal": "4.6",
"phoneNumber": "020 3146 8666",
"comments": "Beautiful location. 
Fantastic food, served with care by attentive staff.
I was genuinely blown away by how incredible the food tasted,
plus an excellent recommendation on wine. 
A pleasant view of Green Park from the window, so you can watch the world go by between courses...
}
```


##### HTTP 204 (No Content)

## Setup
To run this project:

* get APIKey from https://developers.google.com/maps/documentation/places/web-service/get-api-key
* add api apiKey in Service attribute.
* Run it from IntellIJ or from command line:
  `mvn clean install`

## Deployment to Production

* Upload jar to remote machine:
  
  `
  scp -i ${location_pem} ${WORKSPACE_PATH}/restaurants-api/target/restaurants-api-0.0.1-SNAPSHOT.jar ec2-user@ec2-18-130-4-145.eu-west-2.compute.amazonaws.com:/home/ec2-user
  restaurants-api-0.0.1-SNAPSHOT.ja
  `
* Connect to Remote machine:
  
  `ssh -i ${location_pem}  ec2-user@ec2-18-130-4-145.eu-west-2.compute.amazonaws.com`

* Run spring boot app in a remote machine:
  
  A.The process will be automatically killed after ending connection with EC2
  
  `java -jar restaurants-api-0.0.1-SNAPSHOT.jar > output.txt &`
    * to kill it manually:
      
      `ps`
      
      `ps | grep java`  
      
      `kill -9 process ref`
      
  B. Keep the process as a daemon (even after ending connection):
    * create a screen named restaurants and run the program
      
       `screen -dmS restaurants java -jar restaurants-api-0.0.1-SNAPSHOT.jar > restaurants.log &
       `
    * to kill the process, list all the running process and kill the screen:
      
      `screen -ls
      `  
      `screen -X -S restaurants quit`
  
* Ready to use GET endpoints
