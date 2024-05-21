# Airtable.java - The Java API for Airtable

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/stritti/airtable-java?utm_source=github.com&utm_medium=referral&utm_content=Sybit-Education/airtable.java&utm_campaign=badger)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/25c71982881d40eeb1517e65827f5c62)](https://www.codacy.com/app/Sybit-Education/airtable-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Sybit-Education/airtable.java&amp;utm_campaign=Badge_Coverage)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)

This is a Java API client for Airtable (<http://www.airtable.com>).

The Airtable API provides a simple way of accessing data within Java projects.

More information about the Airtable API could be found at [https://airtable.com/api](https://airtable.com/api).
The documentation will provide detailed information about your created base.

# Usage

For adding dependency, you could use bintray-repository:
[https://bintray.com/sybit-education/maven/airtable.java](https://bintray.com/sybit-education/maven/airtable.java)

The files are stored at: [https://dl.bintray.com/sybit-education/maven/](https://dl.bintray.com/sybit-education/maven/)

## Gradle

For Gradle add compile `com.sybit:airtable.java:[version]` to compile dependencies.
Also add repository to dependencies:

```
repositories {
  maven {
    url = uri("https://maven.pkg.github.com/Sybit-Education/airtable.java")
    ...
  }
}
```

## Initializing

It is required to initialize the Java API before it is used. At leased you have to pass your API-Key to get
access to Airtable:

```Java
Airtable airtable = new Airtable().configure();
```

### Personal Access Token

The personal access token could be passed to the app in different ways:

* Defining Java property `AIRTABLE_TOKEN` (e.g. `-DAIRTABLE_TOKEN=foo`).
* Defining OS environment variable `AIRTABLE_TOKEN` (e.g. `export AIRTABLE_TOKEN=foo`).
* Defining property file `credentials.properties` in root classpath containing key/value `AIRTABLE_TOKEN=foo`.
* On the other hand the token could also be added by using the method `Airtable.configure(String token)`.

#### How to get Personal Access Token

See: <https://support.airtable.com//docs/creating-personal-access-tokens>-

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

### Connecting to Airtable

To use this libraray you will need an Airtable object. Simply create one: `Airtable airtable = new Airtable();`.
This object needs an API-Key or it won't work properly so `airtable.configure(AIRTABLE_API_KEY);`.
Now the Airtable object needs to know which base you want to access. This method will return a Base object which will be used in the future:
`Base base = airtable.base(AIRTABLE_BASE);`

With the Base object you can perform all kind of operations see more at [CRUD Operations on Table Records](#crud-operations-on-table-records).

## Object Mapping

The Java implementation of the Airtable API provides automatic object mapping. You can map any table to your own Java classes.
But first you need to specify those classes.

### Create a Object

The Java objects represent records or 'values' in Airtable. So the class attributes need to be adjusted to the Airtable Base.

#### Example

In Airtable we got a table 'Actor'. The columns represent the class attributes.

This is how our 'Actor' table looks like:

| Index |     Name      |    Photo    | Biography |         Filmography          |
| :---: | :-----------: | :---------: | :-------: | :--------------------------: |
|   1   | Marlon Brando | Some Photos | Long Text | Reference to the Movie Table |
|   2   |  Bill Murray  | Some Photos | Long Text | Reference to the Movie Table |
|   3   |   Al Pacino   | Some Photos | Long Text | Reference to the Movie Table |
|  ...  |      ...      |     ...     |    ...    |             ...              |

Now our Java class should look like this:

```Java
  public class Actor {

      private String id;
      @SerializedName("Name")
      private String name;
      @SerializedName("Photo")
      private List<Attachment> photo;
      @SerializedName("Biography")
      private String biography;
      @SerializedName("Filmography")
      private String[] filmography;

      public String getId() {
          return id;
      }

      public void setId(String id) {
          this.id = id;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String[] getFilmography() {
          return filmography;
      }

      public void setFilmography(String[] filmography) {
          this.filmography = filmography;
      }

      public List<Attachment> getPhoto() {
          return photo;
      }

      public void setPhoto(List<Attachment> photo) {
          this.photo = photo;
      }

      public String getBiography() {
          return biography;
      }

      public void setBiography(String biography) {
          this.biography = biography;
      }
  }
```

For each column we give the Java class an attribute with the column name (Be careful! See more about naming in the Section [Annotations](#annotations))
and add Getters and Setters for each attribute. The attribute types can be either primitive Java types like `String` and `Float` for Text and Numbers,
`String Array` for references on other Tables or `Attachment` for attached photos and files.

Now we got everything we need to create our first Airtable table object.
We use the Java class we just wrote to specify what kind of Object should be saved in our table. Then we tell our `base`-object which table we want to access.
All the records saved in our Airtable Base now should be in our local Table<JAVA CLASS> Object.

Example:

```Java
Base base = airtable.base('AIRTABLE_API_KEY');
Table<JAVA CLASS> actorTable = base.table("NAME OF THE TABLE", <JAVA_CLASS>);
//Example with the Actor Table
Table<Actor> actorTable = base.table("Actors", Actor.class);
```

### Basic Objects

The Java implementation of the Airtable-API provides an implementation of basic Airtable objects such as attachments and thumbnails.  
Photos and attached files in Airtable are retrieved as `Attachment`s. Photos furthermore contain `Thumbnail`-Objects for different sizes.

#### Attachment

All the `Attachment`-objects got the following attributes:

* String `id`
* String `url`
* String `filename`
* Float `size`
* String `type`

Photos additionally have:

* Map<String,Thumbnail> `thumbnails`

#### Thumbnails

A Thumbnail is generated for image files in Airtable. Thumbnails are bound to an `Attachment`-object as a key/value Map.
The keys are `small` and `large` for the different sizes. The value is a `Thumbnail`-object.

A `Thumbnail`-object got the following Attributes:

* String `name`
* String `url`
* Float `width`
* Float `height`

Note: The `name` of a Thumbnail Object is identical with it´s key ( `small` or `large` ).

### Annotations

Use the annotation `@SerializedName` of [Gson](https://github.com/google/gson) to annotate column names containing `-`, empty characters or other not in Java mappable characters.
The airtable.java API will respect these mappings automatically.

#### Example

```Java
import com.google.gson.annotations.SerializedName;

//Column in Airtable is named "First- & Lastname", which is mapped to field "name".
@SerializedName("First- & Lastname")
private String name;
```

### Sort

With the integrated `Sort` element you can retrieve a list of sorted objects that specifies how the records will be ordered.
Each sort object must have a field key specifying the name of the field to sort on, and an optional direction key that is either "asc" or "desc".
The default direction is "asc".

For example, to sort records by Name, pass in:

```Java
Sort sort = new Sort("Name", Sort.Direction.desc);
List<Movie> listMovies = movieTable.select(sort);
```

If you set the view parameter, the returned records in that view will be sorted by these fields.

Detailed example see [TableParameterTest](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableParameterTest.java)

## CRUD-Operations on Table Records

## Select

Select list of items from table:

* `table(name).select()`: get all records of table `name`
* `table(name).select(Integer maxRecords)`: get max `maxRecords` records of table `name`
* `table(name).select(String[] fields)`: get records of table `name` with only the specified `fields`
* `table(name).select(String view)`: get records of table `name` with the specified `view` (more about [views](https://support.airtable.com/hc/en-us/sections/200644955-Views))
* `table(name).select(Sort sortation)`: get records of table `name` using `sort` to sort records (More about Sort [here](#sort))
* `table(name).select(Query query)`: get records of table `name` using `query` to filter

### Example

```Java
Base base = new Airtable().base("AIRTABLE_BASE");
List<Movie> retval = base.table("Movies", Movie.class).select();
```

Detailed example see [TableSelectTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableSelectTest.java)

### API Result Limitation

The REST-API of Airtable is limited to return max. 100 records. If the select has more than 100 records in result an `offest` is added to
returned data. The Airtable.java client will solve this and tries to load the offset data automatically.

## Find

Use `find` to get specific records of table:

* `table(name).find(String id)`: get record with `id` of table `name`

### Example

```Java
Base base = new Airtable().base("AIRTABLE_BASE");
Table<Actor> actorTable = base.table("Actors", Actor.class);
Actor actor = actorTable.find("rec514228ed76ced1");
```

Detailed example see [TableFindTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableFindTest.java)

## Destroy

Use `destroy` to delete a specific records of table:

* `table(name).destroy(String id)`: delete record with `id` of table `name`

### Example

```Java
Base base = airtable.base("AIRTABLE_BASE");
Table<Actor> actorTable = base.table("Actors", Actor.class);
actorTable.destroy("recapJ3Js8AEwt0Bf");   
```

Detailed example see [TableDestroyTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableDestroyTest.java)

## Create

First build your record. Then use `create` to generate a specific records of table:

* `Table<Actor> actorTable = base.table("Actors", Actor.class);
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

Detailed example see [TableCreateRecordTest.java](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableCreateRecordTest.java)

## Update

Use `update` to update a record of table:

* `Actor.setName("New Name");`: set or update to a new Value

   `Actor test = actorTable.update(Actor);`: update the Actor in the Table

### Example

```Java
// detailed Example see TableCreateTest.java

Actor marlonBrando = ...;

marlonBrando.setName("Marlon Brando");
Actor updated = actorTable.update(marlonBrando);
```

Detailed example see [TableUpdateTest](https://github.com/Sybit-Education/airtable.java/blob/develop/src/itest/java/com/sybit/airtable/TableUpdateTest.java)

# Roadmap

Short overview of features, which are supported:

* [x] Airtable Configure
  * [x] configuration of `proxy`
  * [ ] configure `user`/`password` for proxy
  * [x] configuration of `AIRTABLE_API_KEY` & `AIRTABLE_BASE`
  * [x] configuration of `requestTimeout`

* [x] Select Records
  * [x] SelectAll
  * [x] Queries (`maxRecords`, `sort` & `view` )
  * [x] Support of `filterByFormula`
  * [x] Support of `paging`
  * [x] Support of appending `offset` data

* [x] Find Record
* [x] Create Record
* [x] Update Record
* [ ] Replace Record (could be done by update)
* [x] Delete/Destroy Record
* General requirements
  * [x] Automatic ObjectMapping
    * [x] Read: convert to Objects
    * [x] Read: conversion of `Attachment`s & `Thumbnail`s
    * [x] Write: convert Objects to JSON
* [x] Errorhandling

# Contribute

see: [CONTRIBUTING.md](./CONTRIBUTING.md)

## Testing

There are **JUnit tests** and integration tests to verify the API.

For unit testing, the JSON-responses are mocked by [WireMock](http://wiremock.org/).

The **integration tests** are based on the [Airtable template Movies](https://airtable.com/templates/groups-clubs-and-hobbies/exprTnrH3YV8Vv9BI/favorite-movies), which could be created in your own account to run integration tests. Add your configuration of `AIRTABLE_API_KEY` & `AIRTABLE_BASE` to the integration tests locally.


# Other Airtable Projects

* [Airtable.js](https://github.com/Airtable/airtable.js): official JavaScript Client
* [Airtable Ruby Client](https://github.com/Airtable/airtable-ruby): Ruby Client
* [Airtable Python](https://github.com/nicocanali/airtable-python): Python Client
* [Airtabler](https://github.com/bergant/airtabler): R interface to the Airtable API
* [Airtable.cs](https://github.com/alphamax/AirTable.cs): AirTable API .Net client.
* and more Github-Projects using topic *[Airtable](https://github.com/search?q=topic%3Aairtable&type=Repositories)*

# Credits

Thank you very much for these great frameworks and libraries provided open source!

We use following libraries:

* [unirest](http://unirest.io/java.html)
* [Google gson](https://github.com/google/gson)
* [Apache Commons Beanutils](http://commons.apache.org/proper/commons-beanutils/)
* [Apache Commons IO](http://commons.apache.org/proper/commons-io/)
* [slf4j](https://www.slf4j.org)
* [JUnit](http://junit.org)
* [WireMock](http://wiremock.org/)

# License

MIT License, see [LICENSE](LICENSE)
