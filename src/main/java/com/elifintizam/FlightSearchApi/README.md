# Flight Search API

Flight Search API is a backend API that I developed using Spring Boot and PostgreSQL for the Amadeus Case Study.

### It has:
- CRUD operations.
- One way and two way flight search operation.
- A scheduled job that fetch flight data from mock api.
- Authentication configurations.
- API documentation with Swagger.

## API Usage

1. [All Operation Paths](#all-operation-paths)
2. [One Way Flight Search Request Parameters](#one-way-flight-search-request-parameters)
3. [One Way Flight Search Response](#one-way-flight-search-response)
4. [Two Way Flight Search Request Parameters](#two-way-flight-search-request-parameters)
5. [Two Way Flight Search Response](#two-way-flight-search-response)
6. [Api Usages for CRUD operations](#api-usages-for-crud-operations)

### All Operation Paths

![All](https://github.com/elifintizamoglu/FlightSearchApi/blob/master/src/main/resources/static/ApiScreenShots/all.png)

### One Way Flight Search Request Parameters

![OneWay](https://github.com/elifintizamoglu/FlightSearchApi/blob/master/src/main/resources/static/ApiScreenShots/SearchFlightOne.png)

### One Way Flight Search Response

![OneWayResponse](https://github.com/elifintizamoglu/FlightSearchApi/blob/master/src/main/resources/static/ApiScreenShots/SearchFlightOneResponse.png)

### Two Way Flight Search Request Parameters

![TwoWay](https://github.com/elifintizamoglu/FlightSearchApi/blob/master/src/main/resources/static/ApiScreenShots/SearchFlightTwo.png)

### Two Way Flight Search Response

![TwoWayResponse](https://github.com/elifintizamoglu/FlightSearchApi/blob/master/src/main/resources/static/ApiScreenShots/SearchFlightTwoResponse.png)

### Api Usages for CRUD operations

#### Bring all flights:

```http
  GET /api/flight/get/all
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `-` | `-` | `-` |

#### Bring flight with given id:

```http
  GET /api/get/{flightId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required** |



