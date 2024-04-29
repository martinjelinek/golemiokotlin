# Golemio Kotlin KMM Library

Golemio Kotlin is a Kotlin Multiplatform Mobile (KMM) library for accessing Prague open data from Golemio API. It implements most of the [API documentation](https://api.golemio.cz/docs/public-openapi/) GET methods and provides the data in Kotlin data structures.
This project has been developed as a diplomat thesis on the Faculty of Informatics and Statistics, Prague University of Economics and Business.

## Usage
#### API Key
First of all, you need to get your Golemio API key [here](https://api.golemio.cz/api-keys).
#### Dependency
Then it would help if you implemented this dependency in your build.gradle.kts file:
```kotlin
implementation("io.github.martinjelinek:golemiokotlin:$version")
```
#### Access to the data
The data can be accessed via various clients. 12 clients represent 12 data sets. Those are designed to contain methods to match the documentation [API documentation](https://api.golemio.cz/docs/public-openapi/). Example of usage:

```kotlin
val airQualityClient: AirQualityClient = AirQualityClient("yourApiKey")

val data = airQualityClient.getAllAirQualityStations(
            latlng = Pair("50.124935", "14.457204"),
            range = 5000,
            districts = listOf("praha-4", "praha-5"),
            limit = 100,
            offset = 10,
            updatedSince = "2023-05-18T07:38:37.000Z",
        )
```
###### Available clients:
* AirQualityClient
* BicycleCountersClient
* CityDistrictsClient
* GardensClient
* MedicalInstitutionsClient
* MunicipalAuthoritiesClient
* MunicipalLibrariesClient
* MunicipalPoliceStationsClient
* ParkingClient (v1)
* PlaygroundsClient
* WasteCollectionClient

## License

This project is licensed under the MIT license - see the [LICENSE](https://github.com/martinjelinek/golemiokotlin/blob/main/LICENSE) file for more details.


