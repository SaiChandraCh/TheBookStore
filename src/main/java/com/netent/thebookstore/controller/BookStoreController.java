package com.netent.thebookstore.controller;

import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.services.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
	author: Sai Chandra Chilupui
	last updated date: Sun, 24 May 2020
*/
/*
This is the REST API controller which handles the User requests
* */
@RestController
@RequestMapping(path = "/book-store")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;
    /*This method handles all the requests to add a book to store*/
    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    private HttpEntity<Object> addBook(@RequestBody Book book){
        try {
            bookStoreService.addBook(book);
            return new ResponseEntity(book,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /*This method handles the GET requests to fetch the book using isbn in the store
    * If the book is not available null
    * */
    @RequestMapping(path = "/searchbyisbn/{isbn}", method = RequestMethod.GET)
    private Book getBookByISBN(@PathVariable int isbn){
        return bookStoreService.getBookByIsbn(isbn);
    }

    /*This method handles the GET requests to fetch the book using title in the store
     * If the book is not available null is returned
    * */
    @RequestMapping(path = "/searchbytitle/{title}", method = RequestMethod.GET)
    private HttpEntity<Object> getBookByTitle(@PathVariable String title){
        if(title.trim().isEmpty() || title == null){
            return new ResponseEntity("Missing Mandatory Parameter",HttpStatus.BAD_REQUEST);
        }
        List<Book> books = bookStoreService.getBooksByTitle(title);
        if(books.size() == 0){
            return new ResponseEntity("No book found with given title", HttpStatus.OK);
        }
        return new ResponseEntity(books,HttpStatus.FOUND);
    }

    /*This method handles the GET requests to fetch the book using author in the store
     * If the book is not available null is retured
     * */
    @RequestMapping(path = "/searchbyauthor/{author}", method = RequestMethod.GET)
    private HttpEntity<Object> getBookByAuthor(@PathVariable String author){
        if(author.trim().isEmpty() || author == null){
            return new ResponseEntity("Missing Mandatory Parameter",HttpStatus.BAD_REQUEST);
        }
        List<Book> books = bookStoreService.getBooksByAuthor(author);

        if(books.size() == 0){
            return new ResponseEntity("No book found with given author", HttpStatus.OK);
        }
        return new ResponseEntity(books,HttpStatus.FOUND);
    }

    /*This method handles the GET requests to fetch the book using isbn in the Coverage api
     * If the book is not available null is retured
     * */
    @RequestMapping(path = "/searchcoverage/{isbn}", method = RequestMethod.GET)
    private HttpEntity getBookByCoverage(@PathVariable int isbn){
        List<String> posts = bookStoreService.getBooksMatchedWithCoverage(isbn);
        if (posts.size() == 0){
            return new ResponseEntity("No book found with given author", HttpStatus.OK);
        }
        return new ResponseEntity(posts, HttpStatus.FOUND);
    }

    /*This method handles the PUT requests to buy a book from the store
     * If the book is not available will add the book object to store but this book cannot be sold
     * */
    @RequestMapping(path = "/buy", method = RequestMethod.PUT)
    private HttpEntity<Object> buyBook(@RequestBody Book book) {
        Book bookBought = null;
        try {
            bookBought = bookStoreService.buyBook(book);
            return new ResponseEntity("Please Pay "+bookBought.getPrice()+" Bucks",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}