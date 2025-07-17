# RestAssured Weather Station Project

This project demonstrates how to use RestAssured (Java) to interact with the OpenWeatherMap API for creating and retrieving weather stations.

## Features
- Create a weather station using a POST request
- Retrieve a weather station using a GET request

## Prerequisites
- Java 17 or higher
- Maven
- An OpenWeatherMap API key

## Setup
1. Clone this repository:
   ```sh
   git clone <your-repo-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd RestAssured3rdGroup2025
   ```
3. Build the project and download dependencies:
   ```sh
   mvn clean install
   ```

## Running the Tests
Run the following command to execute the tests:
```sh
mvn test
```

## Project Structure
- `src/test/java/Basic/WeatherAPI.java`: Contains test methods for creating and retrieving weather stations.
- `pom.xml`: Maven configuration file with dependencies for RestAssured and JUnit.

## Example Usage
- The `createWeatherStation` test sends a POST request to create a new station.
- The `getWeatherStation` test sends a GET request to retrieve the created station by its ID.

## Notes
- Update the API key in the test file if needed.
- Make sure your network allows outbound HTTP requests to the OpenWeatherMap API.

## License
This project is for educational purposes.
