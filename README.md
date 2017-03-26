[![Build Status](https://travis-ci.org/Sybit-Education/airtable.java.svg?branch=master)](https://travis-ci.org/Sybit-Education/airtable.java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/stritti/airtable-java?utm_source=github.com&utm_medium=referral&utm_content=Sybit-Education/airtable.java&utm_campaign=badger)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/Sybit-Education/airtable-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Sybit-Education/airtable.java&amp;utm_campaign=Badge_Coverage)
[![Download](https://api.bintray.com/packages/sybit-education/maven/airtable.java/images/download.svg) ](https://bintray.com/sybit-education/maven/airtable.java/_latestVersion) 
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)


# Airtable.java

Java API for Airtable (http://www.airtable.com). The Airtable API provides a simple way of accessing your data within your Java project.


# Usage

For adding dependency, you could use bintray-repository:
[https://bintray.com/sybit-education/maven/airtable.java](https://bintray.com/sybit-education/maven/airtable.java)

The files are stored at: [https://dl.bintray.com/sybit-education/maven/](https://dl.bintray.com/sybit-education/maven/)

## Gradle
For Gradle add compile `com.sybit:airtable.java:[version]` to compile dependencies.
Also add `jcenter` repository to dependencies:
```
repositories {
    jcenter()
    ...
}
```

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

+ `table(name).select()`: get all records of table `name`
+ `table(name).select(Integer maxRecords)`: get max `maxRecords` records of table `name`
+ `table(name).select(Query query)`: get records of table `name` using `query` to filter

+ `...`

### Example
```Java
// detailed Example see TableSelectTest.java
Base base = new Airtable().base(AIRTABLE_BASE);
List<Movie> retval = base.table("Movies", Movie.class).select();
```

## Find
Use Find to get specific records of table:

+ `table(name).find(String id)`: get record with `id` of table `name`

### Example
```Java
// detailed Example see TableFindTest.java
Base base = new Airtable().base(AIRTABLE_BASE);
Table<Actor> actorTable = base.table("Actors", Actor.class);
Actor actor = actorTable.find("rec514228ed76ced1");
```

# Roadmap

+ [x] Select
  + [x] SelectAll
  + [x] Queries (`maxRecords`, `sort` & `view` )
  + [ ] Support of `filterByFormula`
  + [ ] Support of Paging
+ [x] Find Record
+ [ ] Create Record
+ [ ] Update Record
+ [ ] Delete Record
+ [ ] Replace Record

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
We use following libraries:

+ [unirest](http://unirest.io/java.html)
+ [Google gson](https://github.com/google/gson)
+ [Apache Commons Beanutils](http://commons.apache.org/proper/commons-beanutils/)
+ [Apache Commons IO](http://commons.apache.org/proper/commons-io/)
+ [slf4j](https://www.slf4j.org)
+ [JUnit](http://junit.org)
+ [WireMock](http://wiremock.org/)

# License

MIT License, see [LICENSE](LICENSE)


