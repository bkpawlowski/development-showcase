# Development showcase project
This showcase was meant to show implementation of sort of service that meets following requirements:
* Uses JDK 17
* Uses gradle or maven as build tool
* It should process data from multiple data sources
* Data sources returns numbers - type and precision are not relevant
* Data source 1: should fetch numbers from https://www.random.org via its REST API
* Data source 2: should be random features provided by Java Platform
* Data source 3: it could be either in-memory database or standalone
* Optionally data source could be different - it's developers choice
* Data(numbers) from given data sources should be aggregated(summed up together) and returned
* Fetch/aggregation process trigger is up to developer (REST-API, scheduled task/job, etc.)
* Framework choice is up to developer
* Source code should be provided either as a zip/rar package or as a link to publicly available repository

## Techstack
* Java 17 (platform)
* SpringBoot (framework)
* Jackson (json/xml serializer/deserializer)
* Hibernate (persistence api provider)
* Retrofit (declarative http client definition library)
* OkHttp (http client)
* H2 (in memory database)
* Hikari-CP (jdbc datasource connection pooling library)
* Logback + SLF4J (logger and logging facade)
* Lombok (boiler plate code generator)
* Spock Framework (testing framework) + groovy (test source code language)
* WireMock (mocking http resources)
* Gradle (project build tool)

## Solution
* Aggregation process is triggered via REST-API by `GET` calling endpoint `/number/v1/random`.
* Endpoints are NOT secured for convenience
* Business logic and provider components are framework-dependency-free
* Business logic beans are created manually(without component-scan on purpose) - framework-dependency-free, it makes business logic components to be easily adapted to different frameworks
* Business logic provides an interface `NumberProvider` that is implemented by various providers(`java_random`, `random_org`, `persistence`)
* `NumberProvider` operates on `BigDecimal` number representation so it supports not only huge numbers but also numbers with huge precision
* Business logic provides an implementation of `NumberProvider` - `SummingCompositeNumberProvider` - that aggregates `NumberProviders` and performs add operation on returned results
* Requesting `/number/v1/random` resource triggers number fetch process from `SummingCompositeNumberProvider`;
if any of the given provider fails while fetching number `INTERNAL_SERVER_ERROR` status code is returned in response
* Unit tests are implemented
* Simple E2E test of process trigger is implemented
* Swagger docs are exposed
* Health indicators are exposed
* `application.properties` file is neither externalized nor profiled (profiles works out of the box actually)

## Requirements
* JDK 17
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
