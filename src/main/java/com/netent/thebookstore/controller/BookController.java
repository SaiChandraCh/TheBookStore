package com.netent.thebookstore.controller;

import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book-store")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    private String addBook(@RequestBody Book book){
        return null;
    }

    @RequestMapping(path = "/searchbyisbn/{isbn}", method = RequestMethod.GET)
    private List<Book> getBookByISBN(@PathVariable int isbn){
        return null;
    }

    @RequestMapping(path = "/searchbytitle/{title}", method = RequestMethod.GET)
    private List<Book> getBookByTitle(@PathVariable String title){
        return null;
    }

    @RequestMapping(path = "/searchbyauthor/{author}", method = RequestMethod.GET)
    private List<Book> getBookByAuthor(@PathVariable String author){
        return null;
    }

    @RequestMapping(path = "/searchincoverage/{isbn}", method = RequestMethod.GET)
    private List<Book> getBookByCoverage(@PathVariable int isbn){
        return null;
    }
}
