
# Videogame Shop

This is a project made by Sebastian Novoa to showcase his skills as a backend developer. It is trying to simulate a shop that sells Videogame Consoles. The Api should be able to provide basic business functionality while being able to be run locally, containers or in the cloud.


## Tech Stack

**Server:** Docker Image running on a EC2 instance. Using Spring Boot's included Apache Tomcat.

**Database:** AWS DynamoDB instance, JPA and spring-data-dynamodb for easier communication with the database. 

**Application**: OpenJDK Java 8. Spring Boot 2.7.8.


## Run Locally

Clone the project

```bash
  git clone https://github.com/BlueWings98/videogames
```

Go to the project directory

```bash
  cd videogames
```

Install dependencies

```bash
  mvn dependency:copy-dependencies
```

Start the server

```bash
  In the IDE click on Run Java application
```


## Run in Docker

To run this project on a cointainer, first have installed Docker Desktop: https://www.docker.com/products/docker-desktop/

Clone the project

```bash
  git clone https://github.com/BlueWings98/videogames
```

Go to the project directory

```bash
  cd videogames
```
Generate the .jar file

```bash
  mvn package
```

Build the image
```bash
  docker build -t "spring-boot-docker"
```
Run the proyect

```bash
  docker run --name spring-boot-docker -p 8080:8080 spring-boot-docker:latest
```

## API Reference

#### Get all Consoles

```http
  GET /consoles
```

#### Get Console By Id

```http
  GET /consoles/${consoleId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `consoleId`      | `string` | **Required**. Id of item to fetch 


#### Create New Console 

```http
  POST /consoles
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `consoleId`      | `string` | **Required**. Id of new item
| `consoleName`      | `string` | **Required**. Name of the new console |
| `minPrice`      | `string` | **Required**. Min price to sell the item |
| `maxPrice`      | `string` |  Max Price that the item will sell |
| `discount`      | `string` | **Required**. Discount % |

#### Update Console 
```http
  PUT /consoles{consoleId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `consoleId`      | `string` | **Required**. Id of item to modify
| `consoleName`      | `string` | Name of the new console |
| `minPrice`      | `string` | Min price to sell the item |
| `maxPrice`      | `string` |  Max Price that the item will sell |
| `discount`      | `string` |  Discount % |

#### Delete Console 
```http
  DELETE /consoles{consoleId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `consoleId`      | `string` | **Required**. Id of item to delete

#### Sell a Console 
```http
  PUT /consoles{consoleId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `console`      | `string` | **Required**. Name of the console to sell.
| `value`      | `int` | **Required**. Value of the console to sell.

#### Get all Sales

```http
  GET /sales
```

#### Get Sale By Id

```http
  GET /sales/${saleId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `saleId`      | `string` | **Required**. Id of item to fetch 


#### Create New Sale

```http
  POST /sales
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `saleId`      | `string` | **Required**. Id of new item
| `productName`      | `string` | **Required**. Name of the console to sell |
| `price`      | `string` | **Required**. Min price to sell the item |
| `discount`      | `string` |  **Required**. Discount that was given when the item was sold |

#### Update Sale 
```http
  PUT /sales{saleId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `saleId`      | `string` | **Required**. Id of new item
| `productName`      | `string` | Name of the console to sell |
| `price`      | `string` | Min price to sell the item |
| `discount`      | `string` |  Discount that was given when the item was sold |

#### Delete Sale
```http
  DELETE /sales{saleId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `saleId`      | `string` | **Required**. Id of sale to delete.

#### Get all Users

```http
  GET /users
```

#### Get User By Id

```http
  GET /users/${userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `string` | **Required**. Id of user to fetch 


#### Create New User

```http
  POST /users
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `string` | **Required**. Id of new user
| `userName`      | `string` | **Required**. Name of the new user |
| `userRole`      | `string` | **Required**. Role of the user |

#### Update User
```http
  PUT /users{userId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `string` | **Required**. Id of user to modify
| `userName`      | `string` |  New User Name. |
| `userRole`      | `string` | New Role of the user |

#### Delete User
```http
  DELETE /users{userId}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `string` | **Required**. Id of user to delete

#### Generate Discount report

```http
  GET sales/discount/report
```
Returns the value of all the added discounts ever given.

#### Download Exel file to load
```http
  POST /utilities/download
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fileURL`      | `string` | **Required**. http adress of the excel file to download

#### Read and upload database with excel data
```http
  POST /utilities/read
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `inputFilePath`      | `string` | **Required**. Indicate the path of the file to read and populate.



## Lessons Learned

In this project I learned how to use a non relational Database that is located remotely. I also learned to prioritize as I would have loved to do much more with this project but couldn't because of time constrains and burnout. Leaned to make much better README, improved in Docker and explore Spring Boot in a much deeper way. Now I feel capable of doing a good proyect starting from scratch and that was something that I wasn't sure how to do. 

PD: I learned there are libraries that crash the project only because they are imported :(

