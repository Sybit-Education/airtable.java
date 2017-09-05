# Contribute

We are glad to see your pull requests. 

We use Travis as our continous integration build. Because of Travis security reasons on a Pull Request only JUnit tests run. 
The integration tests need a valid AIRTABLE_API_KEY which travis doesent provide on PR's from forked reposiorys.
However the integration tests you wrote will run after the PR is confirmed and merged.

## Current status

The current status of our project is maintained on our agile board:
[Kanban Board of airtable.java](https://github.com/Sybit-Education/airtable.java/projects/1)

## Compiling project

airtable.jave is developed and compiled using Java 8.


We use [Gradle](https://gradle.org) to compile and package project:

+ for tests run: `./gradlew clean test`
+ build jar: `./gradlew jar` (The built JARs will be placed under `build/libs`.)

## Testing

There are JUnit tests and integration tests to verify the API.
The integration tests are based on the Airtable template [Movies](https://airtable.com/templates/groups-clubs-and-hobbies/exprTnrH3YV8Vv9BI/favorite-movies) which could be created in your account.
For testing, the JSON-responses are mocked by [WireMock](http://wiremock.org/). 
