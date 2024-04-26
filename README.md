# Advance MongoDB Aggregations with SpringBoot using Amazon Coretto JDK

## Blog Post

The code in this repository is discussed in [this blog post](https://www.mongodb.com/developer/languages/java/aggregation-framework-springboot-jdk-coretto/) in the [MongoDB Developer Center](https://www.mongodb.com/developer/).

## Supported versions:

- Java 21
- Spring boot 4.2.2
- MongoDB 6.0
- MongoDB Java driver 4.11.0
- Maven 3.9.6

## Commands

- Start the server in a console with `mvn spring-boot:run`.
- You can build the project with : `mvn clean package`.
- You can run the project with the fat jar and the embedded Tomcat: `java -jar target/springboot-mongo-atlas-0.0.1-SNAPSHOT.jar` but I would use a real tomcat in production.

## Example API Calls

```bash
curl -X 'GET' 'curl http://localhost:8080/api/sales' -H 'accept: */*'
```
```bash
curl -X 'GET' 'curl http://localhost:8080/api/sales/65e1a2627bb4f16e57daadff' -H 'accept: */*'
```

```bash
curl -X PUT -H "Content-Type: application/json" -d '{
  "id": "5bd761dcae323e45a93cd068",
  "saleDate": "2013-10-14T20:05:16.962Z",
  "items": [
    {
      "name": "notepad",
      "tags": [ "office", "writing", "school" ],
      "price": "35.02",
      "quantity": 3
    },
    {
      "name": "notepad",
      "tags": [ "office", "writing", "school" ],
      "price": "22.7",
      "quantity": 2
    }
  ],
  "storeLocation": "London",
  "customer": { "gender": "M", "age": 32, "email": "erro@finhu.gn", "satisfaction": 4 },
  "couponUsed": true,
  "purchaseMethod": "In store"
}' 'http://localhost:8080/api/sales/updateUser'
```

```bash
curl -X 'DELETE' 'http://localhost:8080/api/sales/deleteUser/5bd761dcae323e45a93cd068' -H 'accept: */*'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/London' -H 'accept: */*'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/groupStage/Denver' -H 'accept: */*'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/TotalSales' -H 'accept: */*'
```
```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/PopularItem' -H 'accept: */*'
```
```bash
curl -X 'GET' 'http://localhost:8080/api/sales/aggregation/buckets' -H 'accept: */*'
```

## Author

Aasawari Sahasrabuddhe

- aasawari.sahasrabuddhe@mongodb.com
- aasawariMongoDB on [GitHub](https://github.com/mongodb-developer/spring-boot-mongodb-aggregations)
- Aasawari Sahasrabuddhe in the [MongoDB Developer Community forum](https://www.mongodb.com/community/forums/u/aasawari/summary).
