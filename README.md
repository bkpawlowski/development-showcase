# Development showcase project

## Requirements
* JDK 11
* A bit of gradle knowledge

## Running unit tests
```
gradlew test
```
## Running integration tests
```
gradlew testIntegration
```
## Building executable jar
```
gradlew bootJar
```

## Running directly from gradle
```
gradlew bootRun
```

## Running from previously build executable jar
it will work as long as nobody touched gradle project descriptor
```
java -jar build/libs/development-showcase-1.0.jar
```

## Accessing app endpoints
These are unsecured for ease of access purpose

### Random number sum
```
curl localhost:8200/number/v1/random
```

### Swagger
REST API documentation
```
curl localhost:8200/v2/api-docs
```
### Health
Application health indicators
```
curl localhost:8200/actuator/health
```


