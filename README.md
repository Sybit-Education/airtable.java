[![Build Status](https://travis-ci.org/Sybit-Education/airtable.java.svg?branch=master)](https://travis-ci.org/Sybit-Education/airtable.java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/stritti/airtable-java?utm_source=github.com&utm_medium=referral&utm_content=Sybit-Education/airtable.java&utm_campaign=badger)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)


# Airtable.java

Java API for Airtable (http://www.airtable.com). The Airtable API provides a simple way of accessing your data within your Java project.


# Usage

## Initializing

### API-Key
The API key could be passed to the app by 
+ defining Java property `AIRTABLE_API_KEY` (e.g. `-DAIRTABLE_API_KEY=foo`).
+ defining OS environment variable `AIRTABLE_API_KEY` (e.g. `export AIRTABLE_API_KEY=foo`).
+ defining property file `credentials.properties` in root classpath containing key/value `AIRTABLE_API_KEY=foo`.
+ On the other hand the API-key could also be added by using the method `Airtable.configure(String apiKey)`.

#### How to get API-Key
See: https://support.airtable.com/hc/en-us/articles/219046777-How-do-I-get-my-API-key-

### Proxy Support
The API supports environment variable `http_proxy`. If the variable is set, it is used automatically.

* On Windows: `set http_proxy=http://your_proxy:your_port`
* On Unix/OS X: `export http_proxy=http://your_proxy:your_port`

## Access Base

## Access Table 

## CRUD-Operations on table items

## Select
Select List of items from table:

+ `table(name).select()`: get all items of table `name`
+ `table(name).select(Integer maxRows)`: get max `maxRows` items of table `name`
+ `table(name).select(Query query)`: get items of table `name` using `query` to filter

+ `...`

## Find
Use Find to get specific item of table:

+ `table(name).find(String id)`: get item with `id` of table `name`


# Roadmap

## Table Items
+ Select
  + SelectAll
  + Queries
+ Create RowItem
+ Update RowItem
+ Delete RowItem

# Compiling project
We use [Gradle](https://gradle.org) to compile and package project:

+ for tests run: `./gradlew clean test`
+ build jar: `./gradlew jar` (The built JARs will be placed under `build/libs`.)

## Testing
There are JUnit tests to verify the API.
The tests are based on the Airtable template [Movies](https://airtable.com/templates/groups-clubs-and-hobbies/exprTnrH3YV8Vv9BI/favorite-movies
) which could be created in your account.
For testing, the JSON-responses are mocked via [WireMock](http://wiremock.org/). 

# Credits


# License

MIT License, see [LICENSE](LICENSE)


