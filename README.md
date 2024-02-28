# Advance MongoDB Aggregations with SpringBoot using Amazon Coretto JDK


## Blog Post

The code in this repository is discussed in [this blog post](XXX) in the [MongoDB Developer Center](https://www.mongodb.com/developer/).

## Supported versions:

- Java 21
- Spring boot 3.2.2
- MongoDB 7.0
- MongoDB Java driver 4.11.1
- Maven 3.8.7

## Commands

- Start the server in a console with `mvn spring-boot:run`.
- You can build the project with : `mvn clean package`.
- You can run the project with the fat jar and the embedded Tomcat: `java -jar target/springboot-mongo-atlas-0.0.1-SNAPSHOT.jar` but I would use a real tomcat in production.


## Example API Calls

```bash
curl -X 'GET' 'http://localhost:8080/api/persons' -H 'accept: */*'
```

```bash
curl -X 'GET' \
  'http://localhost:8080/api/sales/aggregation/London -H 'accept: */*'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/groupStage/Denver' -H 'accept: */*'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/TotalSales' -H 'accept: */*'
```

## Author

Aasawari Sahasrabuddhe

- aasawari.sahasrabuddhe@mongodb.com
- aasawariMongoDB on [GitHub](https://github.com/aasawariMongoDB/tutorialCRUD)
- Aasawari Sahasrabuddhe in the [MongoDB Developer Community forum](https://www.mongodb.com/community/forums/u/aasawari/summary).