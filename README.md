

# <h1 align="center"> Blogging Platform APIs </h1>
<p align="center">
<a href="Java url">
<img alt="Java" src="https://img.shields.io/badge/Java-17-darkblue.svg" />
</a>
<a href="Maven url" >
<img alt="Maven" src="https://img.shields.io/badge/maven-4.0-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
<img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.3-brightgreen.svg" />

</p>

 

---

 


## Overview
The Blogging Platform APIs project aims to provide a comprehensive and extensible set of APIs for building a modern and feature-rich blogging platform. With a focus on user management, posts, likes, follows, comments, and more, these APIs serve as the backbone of a blogging system that can be used as a foundation for creating a dynamic and interactive blogging website or application.




## API Endpoints


```http
POST /user/sign up
```

 Description                |
 :------------------------- |
Register a new user. |


 Request Body                |
 :------------------------- |
This endpoint expects an User object with user registration details. |

```http
 POST /user/sign in
```

 Description                |
 :------------------------- |
 Authenticate a user and generate a token. |


 Request Body                |
 :------------------------- |
This endpoint expects an SignInInputDTO object with user login credentials. |


```http
POST /new/post
```

 Description                |
 :------------------------- |
Create a new post. |


 Request Body                |
 :------------------------- |
This endpoint expects a PostingDTO object with the post details. |


```http
 POST /like/{postId}
```

 Description                |
 :------------------------- |
 Like a specific post. |


 Request Body                |
 :------------------------- |
This endpoint expects a Like object, which includes information about the like. |


```http
POST /follow/{targetUserId}
```

 Description                |
 :------------------------- |
Follow another user. |


 Request Body                |
 :------------------------- |
This endpoint expects a Follow object, which contains information about the follow relationship. |


```http
 POST /comment/post/{postId}
```

 Description                |
 :------------------------- |
Add a new comment to a specific post. |


 Request Body                |
 :------------------------- |
This endpoint expects a CommentDTO object with the comment details. |


```http
PUT /post/{postId}
```

 Description                |
 :------------------------- |
 Update an existing post by its postId. |


 Request Body                |
 :------------------------- |
This endpoint expects a Post object in the request body, containing the updated post details. |


```http
PUT /comment/{commentId}
```

 Description                |
 :------------------------- |
Update an existing comment by its commentId. |


 Request Body                |
 :------------------------- |
This endpoint expects a CommentDTO object in the request body, containing the updated comment details. |


```http
 GET /post/{postId}
```

 Description                |
 :------------------------- |
Get information about a specific post by its postId. |


 Response                |
 :------------------------- |
The API will return the details of the post. |


```http
 GET /user/{userId}/posts
```

 Description                |
 :------------------------- |
Retrieve posts made by a specific user. |


 Response                |
 :------------------------- |
 The API will return a list of posts created by the user. |


```http
GET /likes/post/{postId}
```

 Description                |
 :------------------------- |
 Retrieve the likes on a specific post. |


 Response                |
 :------------------------- |
The API will return a list of users who liked the post. |


```http
GET /comments/post/{postId}
```

 Description                |
 :------------------------- |
Retrieve comments on a specific post. |


 Response                |
 :------------------------- |
The API will return a list of comments on the post. |


```http
 DELETE /user/sign out
```

 Description                |
 :------------------------- |
 Sign out the current user, invalidating the authentication token. |


```http
DELETE /user/post/{postId}
```

 Description                |
 :------------------------- |
 Delete a user's own post by postId. |


```http
 DELETE /unlike/post/{postId}
```

 Description                |
 :------------------------- |
Remove a like from a specific post by postId. |



```http
 DELETE /unfollow/user/{targetUserId}
```

 Description                |
 :------------------------- |
Unfollow another user by targetUserId. |

```http
DELETE /post/comment/{commentId}
```

 Description                |
 :------------------------- |
 Delete a comment by commentId. |











## Schemas

The project uses the following data transfer objects (DTOs) and data models:

`AuthenticationDTO :` A data structure for user authentication.

`Follow: `  A schema to represent the follow relationship.

`Like: `   A schema for liking a post.

`Post: `   A schema to represent a post.

`PostingDTO: ` A data structure for creating a new post.

`User: `A schema to represent user data.

`CommentDTO: ` A data structure for creating a new comment.

`SignInInputDTO: ` Data transfer object for user sign-in.

`Comment: ` A schema to represent a comment.

## Deployment

Follow these steps to set up and run the project:



- `Prerequisites: ` Ensure you have Java and your preferred IDE (e.g., IntelliJ, Eclipse) installed.

- `Clone the Repository: `Clone this repository to your local machine.

- `Configuration:` Set up the database configuration and ensure all necessary dependencies are installed.

- `Build and Run:` Build the project and run the application.

- `Testing:` Test the API using tools like Postman or a web browser by sending requests to the defined endpoints.

## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven
- **Database:** MySQL Workbench
- **API Framework:** RESTful APIs
- **API Documentation:** Swagger
- **Deployment:** AWS (EC2 Machine)


## Usage

You can use the provided endpoints to interact with the User Controller API. Ensure you have the necessary authentication and permissions to perform various actions.

Example requests can be found in the API documentation, along with the expected request and response formats.

## Security
It's essential to secure your API, especially the authentication and authorization mechanisms. Ensure that you follow best practices for user authentication and data protection.
## Support

For support, email iamrebelsk@gmail.com or connect with my LinkedIn [![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rebel-sk-55814a1a4/)

