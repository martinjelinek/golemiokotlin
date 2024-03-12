# Golemio Kotlin KMM Library

Golemio Kotlin is a Kotlin Multiplatform Mobile (KMM) library for accessing air quality data. It allows developers to retrieve air quality information from various sources using a unified API.

## Features

- Retrieve air quality data from different stations.
- Easy-to-use API for integrating air quality data into your Kotlin Multiplatform projects.

## Usage
#### API Key
First of all, you need to get your Golemio API key [here](https://api.golemio.cz/api-keys).
#### Dependency
Then you need to implement this dependency in your build.gradle.kts file:
```kotlin
implementation("cz.vse.golemiokotlin:lib:$version")
```
#### Access to the data
The data can be accessed via various clients. There are 12 data sets represented by 12 clients:

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

those clients contain methods to match the documentation [API documentation]([https://api.golemio.cz/docs/public-openapi/)https://api.golemio.cz/docs/public-openapi/]).
