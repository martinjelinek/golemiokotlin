# Golemio Kotlin KMM Library

Golemio Kotlin is a Kotlin Multiplatform Mobile (KMM) library for accessing Prague open data from Golemio API. It implement most of the [API documentation](https://api.golemio.cz/docs/public-openapi/) GET methods and provides the data in kotlin data structures.
This project has been developed as a diplomat thesis on Faculty of Informatics and Statistics, Prague University of Economics and Bussiness.

## Usage
#### API Key
First of all, you need to get your Golemio API key [here](https://api.golemio.cz/api-keys).
#### Dependency
Then you need to implement this dependency in your build.gradle.kts file:
```kotlin
implementation("cz.vse.golemiokotlinlib:$version")
```
#### Access to the data
The data can be accessed via various clients. There are 12 data sets represented by 12 clients. Those are designed to contain methods to match the documentation [API documentation](https://api.golemio.cz/docs/public-openapi/). Example of usage:

```kotlin
val airQualityClient: AirQualityClient = AirQualityClient("yourApiKey")

val data = client.getAllAirQualityStations(
            latlng = Pair("50.124935", "14.457204"),
            range = 5000,
            districts = listOf("praha-4"),
            limit = 100,
            offset = 10,
            updatedSince = "2023-05-18T07:38:37.000Z",
        )
```

* AirQualityClient
  - getAllAirQualityStations
  - getAirQualityStationsHistory
* BicycleCountersClient
  - getAllBicycleCounters
  - getBicycleCountersDetections
  - getBicycleCountersTemperatures
* CityDistrictsClient
* GardensClient
* MedicalInstitutionsClient
* MunicipalAuthoritiesClient
* MunicipalLibrariesClient
* MunicipalPoliceStationsClient
* ParkingClient (v1)
* PlaygroundsClient
* WasteCollectionClient


