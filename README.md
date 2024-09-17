# Synopsis

To Do Spring Boot App

# Services available

- **Search People From Star Wars API** 

- **Search People From DB**

- **Save Search Results To DB**


# Installation

Make sure that the following are installed on your machine:

- **Java 17**
- **Maven**


# Running the application

  - Maven is required to build OR run the Spring Boot application, Spring Boot application runs on the default port, 8080
  - To build the project, run ```mvn install``` on the project root folder
  - To run the project, either run by ```mvn spring-boot:run``` on the project root folder, OR
  - Run ```java -jar ./target/star-wars-trivia-1.0.0.jar``` on the project root folder

# GraphQL Endoint

**URL** : `/graphql`

# GraphIQL Endoint

**URL** : `/graphiql?path=/graphql`

# GraphQL Query

Search People From Star Wars API and Search People From DB

```json
{
  query searchPeople($searchText: String!) {
    searchPeople(searchText: $searchText) {
      people {
        name
        films {
          title
          episodeId
          director
          producer
          releaseDate
        }
        vehicles {
          name
          model
          manufacturer
          costInCredits
          length
          maxAtmospheringSpeed
          crew
          passengers
          cargoCapacity
          consumables
          vehicleClass
        }
      }
    }
  }
}
```

# GraphQL Query Sample Response

```json
{
  "data": {
    "searchPeopleFromDB": {
      "people": [
        {
          "name": "Luke Skywalker",
          "films": [
            {
              "title": "Return of the Jedi",
              "episodeId": 6,
              "director": "Richard Marquand",
              "producer": "Howard G. Kazanjian, George Lucas, Rick McCallum",
              "releaseDate": "1983-05-25"
            },
            {
              "title": "Revenge of the Sith",
              "episodeId": 3,
              "director": "George Lucas",
              "producer": "Rick McCallum",
              "releaseDate": "2005-05-19"
            }
          ],
          "vehicles": [
            {
              "name": "Snowspeeder",
              "model": "t-47 airspeeder",
              "manufacturer": "Incom corporation",
              "costInCredits": "unknown",
              "length": "4.5",
              "maxAtmospheringSpeed": "650",
              "crew": "2",
              "passengers": "0",
              "cargoCapacity": "10",
              "consumables": "none",
              "vehicleClass": "airspeeder"
            },
            {
              "name": "Imperial Speeder Bike",
              "model": "74-Z speeder bike",
              "manufacturer": "Aratech Repulsor Company",
              "costInCredits": "8000",
              "length": "3",
              "maxAtmospheringSpeed": "360",
              "crew": "1",
              "passengers": "1",
              "cargoCapacity": "4",
              "consumables": "1 day",
              "vehicleClass": "speeder"
            }
          ]
        }
      ]
    }
  }
}
```

# GraphQL Mutation

Save Search Results To DB

```json
  mutation saveSearchResult($searchResultInput: SearchResultInput!) {
    saveSearchResult(searchResultInput: $searchResultInput) {
      people {
        name
        films {
          title
          episodeId
          director
          producer
          releaseDate
        }
        vehicles {
          name
          model
          manufacturer
          costInCredits
          length
          maxAtmospheringSpeed
          crew
          passengers
          cargoCapacity
          consumables
          vehicleClass
        }
      }
    }
  }
```

# GraphQL Mutation Sample Input

```json
{
  "searchResultInput": {
      "people": [
        {
          "name": "Luke Skywalker",
          "height": null,
          "mass": null,
          "gender": null,
          "films": [
            {
              "title": "A New Hope",
              "episodeId": 4,
              "director": "George Lucas",
              "producer": "Gary Kurtz, Rick McCallum",
              "releaseDate": "1977-05-25"
            },
            {
              "title": "The Empire Strikes Back",
              "episodeId": 5,
              "director": "Irvin Kershner",
              "producer": "Gary Kurtz, Rick McCallum",
              "releaseDate": "1980-05-17"
            }
          ],
          "vehicles": [
            {
              "name": "Snowspeeder",
              "model": "t-47 airspeeder",
              "manufacturer": "Incom corporation",
              "costInCredits": "unknown",
              "length": "4.5",
              "maxAtmospheringSpeed": "650",
              "crew": "2",
              "passengers": "0",
              "cargoCapacity": "10",
              "consumables": "none",
              "vehicleClass": "airspeeder"
            },
            {
              "name": "Imperial Speeder Bike",
              "model": "74-Z speeder bike",
              "manufacturer": "Aratech Repulsor Company",
              "costInCredits": "8000",
              "length": "3",
              "maxAtmospheringSpeed": "360",
              "crew": "1",
              "passengers": "1",
              "cargoCapacity": "4",
              "consumables": "1 day",
              "vehicleClass": "speeder"
            }
          ]
        }]
	}
}
```