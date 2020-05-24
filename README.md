# The Book Store Rest API

This is a simple project providing the REST API to `Add a book to a store`, `Search a book in a store with contraints`, `Search the title of the book is present in an external`, `Buy a book`.

## This project has 4 components 
    BookStoreController
    BookStoreService
    Book 
    BooksRepository

Let's start with the `Book` and `BooksRepository`. These two components come's under Data Access. Where Book is the MODEL and BooksRepository is the REPOSITORY. Every object that is stored in BookRepository is of type Book.

`BookStoreService` is the integral part of the project which interacts with the DAO's(Data Access Object) and as well as Web layer. Major business logic resides here.

Lastly the `BookStoreController` the web layer of the project, this is where the user or any client interacts with the project.
The request is submitted to the web layer and even the response is returned by the web layer.

## Prerequisite's
    Java 8 or above
    
## Getting Started
    
