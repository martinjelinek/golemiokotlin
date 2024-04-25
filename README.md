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

val data = airQualityClient.getAllAirQualityStations(
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

## License

MIT License

Copyright (c) 2024 Martin Jelínek

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


