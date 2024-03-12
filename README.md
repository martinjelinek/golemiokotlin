# Golemio Kotlin KMM Library

Golemio Kotlin is a Kotlin Multiplatform Mobile (KMM) library for accessing air quality data. It allows developers to retrieve air quality information from various sources using a unified API.

## Features

- Retrieve air quality data from different stations.
- Easy-to-use API for integrating air quality data into your Kotlin Multiplatform projects.

## Usage
#### API Key
First of all, you need to get your Golemio API key at [www.](https://api.golemio.cz/api-keys)
#### Dependency
Then you need to implement this dependency in your build.gradle.kts file:
```kotlin
implementation("cz.vse.golemiokotlin:lib:$version")
```
#### Client
and then the data can be accessed via various clients.
