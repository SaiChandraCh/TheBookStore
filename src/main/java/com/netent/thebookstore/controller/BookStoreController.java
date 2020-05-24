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

@RestController
@RequestMapping(path = "/book-store")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;

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

    @RequestMapping(path = "/searchbyisbn/{isbn}", method = RequestMethod.GET)
    private Book getBookByISBN(@PathVariable int isbn){
        return bookStoreService.getBookByIsbn(isbn);
    }

    @RequestMapping(path = "/searchbytitle/{title}", method = RequestMethod.GET)
    private HttpEntity<Object> getBookByTitle(@PathVariable String title){
        if(title.trim().isEmpty() || title == null){
            return new ResponseEntity("Missing Mandatory Parameter",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(bookStoreService.getBooksByTitle(title),HttpStatus.FOUND);
    }

    @RequestMapping(path = "/searchbyauthor/{author}", method = RequestMethod.GET)
    private HttpEntity<Object> getBookByAuthor(@PathVariable String author){
        if(author.trim().isEmpty() || author == null){
            return new ResponseEntity("Missing Mandatory Parameter",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(bookStoreService.getBooksByAuthor(author),HttpStatus.FOUND);
    }

    @RequestMapping(path = "/searchcoverage/{isbn}", method = RequestMethod.GET)
    private List<String> getBookByCoverage(@PathVariable int isbn){
        return bookStoreService.getBooksMatchedWithCoverage(isbn);
    }

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