# MongoDB CRUD Tutorial with Coretto 21 JDK

> Note for Aasawari: this is just a template. Improve the README and replace everything with your values.

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
- You can run the project with the fat jar and the embedded Tomcat: `java -jar target/springbbot-mongo-atlas-0.0.1-SNAPSHOT.jar` but I would use a real tomcat in production.


## Example API Calls

```bash
curl -X 'POST' \
  'http://localhost:8080/api/person' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "firstName": "Maxime",
  "lastName": "Beugnet",
  "age": 35,
  "address": {
    "number": 123,
    "street": "avenue des Champs-Elysées",
    "postcode": "75000",
    "city": "Paris",
    "country": "France"
  },
  "insurance": true,
  "cars": [
    {
      "brand": "Peugeot",
      "model": "3008",
      "maxSpeedKmH": 280
    }
  ]
}'
```

```bash
curl -X 'GET' 'http://localhost:8080/api/persons' -H 'accept: */*'
```

## Author

Maxime Beugnet

- maxime@mongodb.com
- MaBeuLux88 on [GitHub](https://github.com/mabeulux88)
- MaBeuLux88 in the [MongoDB Developer Community forum](https://www.mongodb.com/community/forums/u/MaBeuLux88/summary).
