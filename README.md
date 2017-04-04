[![Build Status](https://travis-ci.org/Sybit-Education/airtable.java.svg?branch=master)](https://travis-ci.org/Sybit-Education/airtable.java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/stritti/airtable-java?utm_source=github.com&utm_medium=referral&utm_content=Sybit-Education/airtable.java&utm_campaign=badger)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/Sybit-Education/airtable-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Sybit-Education/airtable.java&amp;utm_campaign=Badge_Coverage)
[![Download](https://api.bintray.com/packages/sybit-education/maven/airtable.java/images/download.svg) ](https://bintray.com/sybit-education/maven/airtable.java/_latestVersion) 
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)


# Airtable.java

Java API for Airtable (http://www.airtable.com). The Airtable API provides a simple way of accessing your data within your Java project.

More information about the Airtable API coud be found at [https://airtable.com/api](https://airtable.com/api). 
The documentation will provide detailed information about your created base.

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

It is required to initialize the Java API before it is used. At leased you have to pss your API-Key to get
access to Airtable:

```Java
Airtable airtable = new Airtable().configure();

```

### API-Key
The API key could be passed to the app in different ways: 
* defining Java property `AIRTABLE_API_KEY` (e.g. `-DAIRTABLE_API_KEY=foo`).
* defining OS environment variable `AIRTABLE_API_KEY` (e.g. `export AIRTABLE_API_KEY=foo`).
* defining property file `credentials.properties` in root classpath containing key/value `AIRTABLE_API_KEY=foo`.
* On the other hand the API-key could also be added by using the method `Airtable.configure(String apiKey)`.

#### How to get API-Key
See: https://support.airtable.com/hc/en-us/articles/219046777-How-do-I-get-my-API-key-

### Proxy Support
The API supports environment variable `http_proxy`. If the variable is set, it is used automatically.

* On Windows: `set http_proxy=http://your_proxy:your_port`
* On Unix/OS X: `export http_proxy=http://your_proxy:your_port`

If `endpointUrl` contains `localhost` or `127.0.0.1` proxy settings are ignored automatically.

### Logging

The Simple Logging Facade for Java [SLF4J](https://www.slf4j.org/) serves as a simple facade or abstraction 
for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired 
logging framework at deployment time.

### Request Limits
The API of Airtable itself is limited to 5 requests per second. If you exceed this rate, you will receive a 429 status code and will 
need to wait 30 seconds before subsequent requests will succeed.

## Object Mapping
The Java implementation of the Airtable API provides automatic Object mapping.
 
 *TODO:* 
 * How to create objects
 * Basic objects (attachment, thumbnails, ...)


### Annotations

Use the Gson Annotation `@SerializedName` to annotate Names which contain `-`, emtpy characters or other not mappable characters.
The airtable.java API will respect these mappings automatically.

#### Example
```Java

    import com.google.gson.annotations.SerializedName;

    //Column in Airtable is named "First- & Lastname", which is mapped to field "name".
    @SerializedName("First- & Lastname")
    private String name;
```

## CRUD-Operations on Table Records

## Select
Select list of items from table:

+ `table(name).select()`: get all records of table `name`
+ `table(name).select(Integer maxRecords)`: get max `maxRecords` records of table `name`
+ `table(name).select(Query query)`: get records of table `name` using `query` to filter

+ `...`

### Example
```Java
Base base = new Airtable().base(AIRTABLE_BASE);
List<Movie> retval = base.table("Movies", Movie.class).select();
```

Detailed example see [TableSelectTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/test/java/com/sybit/airtable/TableSelectTest.java)

## Find
Use `find` to get specific records of table:

+ `table(name).find(String id)`: get record with `id` of table `name`

### Example
```Java
Base base = new Airtable().base(AIRTABLE_BASE);
Table<Actor> actorTable = base.table("Actors", Actor.class);
Actor actor = actorTable.find("rec514228ed76ced1");
```

Detailed example see [TableFindTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/test/java/com/sybit/airtable/TableFindTest.java)

## Destroy
Use `destroy` to delete a specific records of table:

+ `table(name).destroy(String id)`: delete record with `id` of table `name`

### Example
```Java
Base base = airtable.base(AIRTABLE_BASE);
Table<Actor> actorTable = base.table("Actors", Actor.class);
actorTable.destroy("recapJ3Js8AEwt0Bf");   
```
Detailed example see [TableDestroyTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/test/java/com/sybit/airtable/TableDestroyTest.java)

## Create
First build your record. Then use `create` to generate a specific records of table:

+ `Table<Actor> actorTable = base.table("Actors", Actor.class);
   Actor newActor = new Actor();
   newActor.setName("Neuer Actor");`: build your record

   `Actor test = actorTable.create(newActor);`: create the recently build record

### Example
```Java
// detailed Example see TableCreateTest.java
Base base = airtable.base("AIRTABLE_BASE");
        
Table<Actor> actorTable = base.table("Actors", Actor.class);
Actor newActor = new Actor();
newActor.setName("Neuer Actor");
Actor test = actorTable.create(newActor);
```

## Update
Use `update` to update a record of table:

+ `Actor.setName("New Name");`: update the value

   `Actor test = actorTable.update(Actor);`: updates the Actor

### Example
```Java
// detailed Example see TableCreateTest.java
Base base = airtable.base("appe9941ff07fffcc");
        
Actor.setName("Neuer Name");
Actor updated = actorTable.update(marlonBrando);
```

# Roadmap

Short overview of features, which are supported:

+ [x] Airtable Configure
  + [x] configuration of `proxy`
  + [x] configuration of `AIRTABLE_API_KEY` & `AIRTABLE_BASE` 
  + [x] configuration of `requestTimeout`

+ [x] Select Records
  + [x] SelectAll
  + [x] Queries (`maxRecords`, `sort` & `view` )
  + [ ] Support of `filterByFormula`
  + [ ] Support of Paging

+ [x] Find Record

+ [x] Create Record
+ [x] Update Record
+ [x] Delete/Destroy Record
+ [ ] Replace Record
+ General requirements
    + [x] Automatic ObjectMapping
      + [x] Read: convert to Objects
      + [x] Read: conversion of `Attachment`s & `Thumbnail`s
      + [x] Write: convert Objects to JSON
  + [x] Errorhandling

# Contribute

We are glad to see your pull requests. 

## Current status

The current status of our project is maintained on our agile board:
[Kanban Board of airtable.java](https://github.com/Sybit-Education/airtable.java/projects/1)

## Compiling project

airtable.jave is developed and compiled using Java 8.


We use [Gradle](https://gradle.org) to compile and package project:

+ for tests run: `./gradlew clean test`
+ build jar: `./gradlew jar` (The built JARs will be placed under `build/libs`.)

## Testing
There are JUnit tests to verify the API.
The tests are based on the Airtable template [Movies](https://airtable.com/templates/groups-clubs-and-hobbies/exprTnrH3YV8Vv9BI/favorite-movies
) which could be created in your account.
For testing, the JSON-responses are mocked via [WireMock](http://wiremock.org/). 

# Credits

Thank you very much for these gread frameworks and ibraries provided open source!
 
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
