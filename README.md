<h1 align="center"> Recipe Management System</h1>

>### Framework Used 
* [SpringBoot](javatpoint.com/spring-boot-tutorial)


>### Language Used
* [Java](https://www.java.com/en/download/help/whatis_java.html)

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Spring Validation
* com.sun.mail
* Swagger openAI

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:
```

spring.datasource.url = jdbc:mysql://localhost:3306/<database name>
spring.datasource.username = <userName>
spring.datasource.password = <password>
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update

spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true

```

## Data Model

The  data model  has the following attributes:
<br>

* Recipe
```
ID: The unique identifier for the recipe.
name: The name of the recipe.
type: The type of recipe, veg or non-veg.
ingrediants: The ingrediants used in recipe.
instructions: The instructions to follow.

Relationships:
User: A many-to-one relationship with the user model.
```

* Comment
```
ID: The unique identifier for the comment.
comment content: The comment on the recipe.

Relationships:

Recipe: A many-to-one relationship with the recipe model.
User: A many-to-one relationship with the user model.

```

* User
```
ID: The unique identifier for the user.
name: The name of the user.
email: The Email address of the user.
password: The password of the user.
gender: The gender of the user.

```

>## Data flow
In this project, we have four layers-
* **Controller** - The controller layer handles the HTTP requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business (service) layer.
* **Repository** - Here database is stored, for which I have used arrayList. In the database layer, CRUD operations are performed.
* **Service** -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.
* **Model** - Here the classes recipe, user, authentication token, comment is defined and the mappings were given to them accordingly.
1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

## DataBase Used
* SQL database
```
I have used Persistent database to implement CRUD Operations.
```

>## Data Structure used in my project
In this project [List](https://www.geeksforgeeks.org/internal-working-of-list-in-java/) and the methods involved with List, like add method for posting new Recipe, remove method for deleting a Recipe, and linear search function for searching a user in Java by specific userId.

>## Project Summary
The Recipe Management system is designed to allow users to store, manage and perform CRUD operations on different recipe data. It provides a RESTful API for interacting with various recipies and users. The application is built using Spring Boot and uses MySQL as the database for storing recipe information. The API endpoints can be explored and tested using Swagger UI.This project basically maintains the upcoming recipe information which includes -
* RecipeId
* RecipeName
* RecipeIngredients
* RecipeInstructions
* Commenter
* RecipeOwner

The above project implements a Recipe Management in which the endpoints provided are:-

* SignUp user
* SignIn user
* Add recipe
* Update recipe
* Get all recipies
* Delete recipe by Id
* Get recipe by Id
* Add comment on recipe
* Get all comments on recipe
* Delete comment by Id
* Sign out user

* The above operations are performed only by successfull authorisation of a particular user.
## Installation and Usage

* Clone the repository to your local machine.
* Make sure you have Java, Maven, and MySQL installed.
* Set up the database configuration in the application.properties file.
* Run the application using Maven or your preferred IDE.
* Access the API endpoints using Swagger UI by navigating to the appropriate URL (e.g., `http://localhost:8080/swagger-ui.html`).

## Author

👤 **Srinath Katta**

* GitHub: [Srinath Katta](https://github.com/Srinathkatta)

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page]("url").
    
---

## Show your support

Give a ⭐️ if this project helped you!
    
---
