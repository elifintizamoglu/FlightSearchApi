# Flight Search API

Flight Search API is a backend API that I developed using Spring Boot and PostgreSQL for the Amadeus Case Study.

### It has:
- CRUD operations.
- One way and two way flight search operation.
- A scheduled job that fetch flight data from mock api.
- Authentication configurations.
- API documentation with Swagger.

## API Usage

All operation paths, flight search requests and responses added as screenshots from swagger. Other requests will be described. 

To try requests yourself please clone this repository to your computer, open it in your IDE, download dependencies by running command:

```bash
  ./mvnw clean install
```
or for Windows: 
```bash
  mvnw.cmd clean install
```
and run the project. Lastly, you can visit [swagger](http://localhost:8080/swagger-ui/index.html) and try the API.

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
  GET /api/flight/get/{flightId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `flightId`      | `Long` | **Required** `Bring flight with given id`|

#### Add flight:

```http
  POST /api/flight/add
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Requests a body`      | `Flight` | `Add flight to database`  |

#### Delete flight:

```http
  DELETE /api/flight/delete/{flightId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `flightId`      | `Long` | **Required** `Delete flight with given id`|


#### Update flight:

```http
  PUT /api/flight/update/{flightId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `flightId, departureAirportId, arrivalAirportId, departureTime, returnTime, price`     | `Long` | **flightId Required** |


#### Bring all airports:

```http
  GET /api/airport/get/all
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `-` | `-` | `-` |

#### Bring airport with given id:

```http
  GET /api/airport/get/{airportId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `airportId`      | `Long` | **Required** `Bring airport with given id`|

#### Add airport:

```http
  POST /api/airport/add
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Requests a body`      | `Airport` | `Add airport to database`  |

#### Delete airport:

```http
  DELETE /api/airport/delete/{airportId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `airportId`      | `Long` | **Required** `Delete airport with given id`|


#### Update airport:

```http
  PUT /api/airport/update/{airportId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `airportId, city`     | `Long` | **Required** |


