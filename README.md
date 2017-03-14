# Airtable.java

Java API for Airtable (http://www.airtable.com). The Airtable API provides a simple way of accessing your data within your Java project.


# Usage

## Initializing

### API-Key
The API key could be passed to the app by defining Java property `AirtableApi` (e.g. `-DAirtableApi=foo`.
On the other hand the key could be added by using the method `Airtable.configure()`.

How to get API-Key, see: [https://support.airtable.com/hc/en-us/articles/219046777-How-do-I-get-my-API-key-]

### Proxy Support
The API supports environment variable `http_proxy`. If the variable is set, it is used automatically.

* On Windows: `set http_proxy=http://your_proxy:your_port`
* On Unix/OS X: `export http_proxy=http://your_proxy:your_port`

## Access Base

## Access Table 

## CRUD-Operations on table items



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

+ for tests run: `gradlew test`

# Credits


# License




