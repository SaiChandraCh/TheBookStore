# The Book Store Rest API

This is a simple project providing the REST API to `Add a book to a store`, `Search a book in a store with contraints`, `Search the title of the book is present in an external`, `Buy a book`.

## This project has 4 components 
    BookStoreController
    BookStoreService
    Book 
    BooksRepository

Let's start with the `Book` and `BooksRepository`. These two components come's under Data Access. Where Book is the MODEL and BooksRepository is the REPOSITORY. Book model has five attributes `isbn`, `title`, `author`, `price`.Every object that is stored in BookRepository is of type Book.

`BookStoreService` is the integral part of the project which interacts with the DAO's(Data Access Object) and as well as Web layer. Major business logic resides here.

Lastly the `BookStoreController` the web layer of the project, this is where the user or any client interacts with the project.
The request is submitted to the web layer and even the response is returned by the web layer.

## Prerequisite's

    Java 8 or above
    maven
    docker
    git
    
## Project SetUp

    git clone https://github.com/SaiChandraCh/TheBookStore.git

The git command helps you to get the project in your workarea. Open the project as maven in your favourite IDE.

## Run the app
    
    mvn package
    
    docker build -f Dockerfile -t thebookstore .
    
    docker run -p 8080:8080 thebookstore
    
The `mvn package` command is used to build the jar.

The `docker build` command is used to build the image.

The `docker run` command is used to run the container.

# REST API

The REST API to the app is described below.

## Add a Book to Store

### Request

`POST /book-store/add`

    {"isbn" : 38412, "title":"dolorem", "author":"cumque", "price":100 }

### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 14:31:33 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74

    {"isbn":38412,"title":"dolorem","author":"cumque","price":100,"count":1}
  

## Search a Book in the Store by using isbn

### Request

`GET /book-store/searchbyisbn/38412`

### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 15:53:37 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74
    

    {"isbn":38412,"title":"dolorem","author":"cumque","price":100,"count":1}

## Search a Book in the Store by using title

### Request

`GET /book-store/searchbyisbn/dolorem`

### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 15:53:37 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74
    

    {"isbn":38412,"title":"dolorem","author":"cumque","price":100,"count":1}

## Search a Book in the Store by using author

### Request

`GET /book-store/searchbyisbn/cumque`

### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 16:14:13 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74
    
    {"isbn":38412,"title":"dolorem","author":"cumque","price":100,"count":1}

## Search a Book in the Coverage api

### Request

`GET /book-store/searchcoverage/38412`

### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 16:18:36 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74

    [
        "eum et est occaecati", 
        "dolorem eum magni eos aperiam quia",
        "dolorem dolore est ipsam",
        "nesciunt iure omnis dolorem tempora et accusantium",
        "in quibusdam tempore odit est dolorem",
        "dolorum ut in voluptas mollitia et saepe quo animi",
        "voluptatem eligendi optio",
        "asperiores ea ipsam voluptatibus modi minima quia sint",
        "maxime id vitae nihil numquam",
        "est et quae odit qui non",
        "doloremque illum aliquid sunt",
        "qui explicabo molestiae dolorem",
        "fuga nam accusamus voluptas reiciendis itaque",
        "eos dolorem iste accusantium est eaque quam",
        "commodi ullam sint et excepturi error explicabo praesentium voluptas",
        "ut numquam possimus omnis eius suscipit laudantium iure",
        "aut quo modi neque nostrum ducimus",
        "soluta aliquam aperiam consequatur illo quis voluptas",
        "sit asperiores ipsam eveniet odio non quia",
        "voluptatum itaque dolores nisi et quasi",
        "voluptas blanditiis repellendus animi ducimus error sapiente et suscipit",
        "aliquid eos sed fuga est maxime repellendus",
        "voluptatem laborum magni",
        "et iusto veniam et illum aut fuga",
        "enim unde ratione doloribus quas enim ut sit sapiente",
        "dignissimos eum dolor ut enim et delectus in",
        "doloremque officiis ad et non perferendis",
        "pariatur consequatur quia magnam autem omnis non amet",
        "labore in ex et explicabo corporis aut quas",
        "tempora rem veritatis voluptas quo dolores vero",
        "beatae soluta recusandae",
        "laboriosam dolor voluptates",
        "temporibus sit alias delectus eligendi possimus magni"
    ]

## Buy a Book in the Store

### Request

    `PUT /book-store/buy`
    
    {"isbn":38412,"title":"dolorem","author":"cumque","price":100,"count":1}
    
### Response
    HTTP/1.1 200 OK
    Date: Sun, 24 May 2020 16:23:42 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74
    
    Please Pay 100 Bucks
    
Note:
    isbn of a book is unique
