package com.netent.thebookstore.services;

import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.dao.repostories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BooksRepository booksRepository;

    public Book addBook(Book book){
        return booksRepository.save(book);
    }

    public List<Book> getBooks(int isbn){
        return booksRepository.findByisbn(isbn);
    }

    public List<Book> getBooksBytitle(String title){
        return booksRepository.findBytitleContaining(title);
    }

    public List<Book> getBookByAuthor(String author){
        return booksRepository.findByauthorContaining(author);
    }
}
