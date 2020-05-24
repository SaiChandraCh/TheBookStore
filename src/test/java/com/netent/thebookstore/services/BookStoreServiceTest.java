package com.netent.thebookstore.services;

import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.dao.repository.BooksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class BookStoreServiceTest {
    @Mock
    private BooksRepository repository;

    @InjectMocks
    private BookStoreService service;

    @Autowired
    BookStoreService bookStoreService;

    @Test
    public void space(){
        assertTrue(" ".trim().isEmpty());
    }
    @Test
    public void testtoaddBook() throws Exception {
        Book newBook = new Book(1235,"Harry Potter","J K Rowling",20);
        given(repository.save(newBook)).willAnswer(object -> object.getArgument(0));
        Book book = service.addBook(newBook);
        assertThat(book).isNotNull();
        verify(repository).save(any(Book.class));
    }

    @Test
    public void testtogetBookByIsbn(){
        Book newBook = new Book(1235,"Harry Potter","J K Rowling",20);
        given(repository.findByisbn(newBook.getisbn())).willReturn(new Book(1235,"Harry Potter","J K Rowling",120));
        Book matchedBook = repository.findByisbn(newBook.getisbn());
        assertThat(matchedBook).isNotNull();
    }

    @Test
    public void testtogetBookByAuthor(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1235,"Harry Potter","J K Rowling",120));
        Book newBook = new Book(1235,"Harry Potter","J K Rowling",20);
        given(repository.findAllByauthorContainingIgnoreCase("rowling")).willReturn(books);
        List<Book> matchedBooks = repository.findAllByauthorContainingIgnoreCase("rowling");
        assertThat(matchedBooks).isNotNull();
    }

    @Test
    public void testtogetBookByTitle(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1235,"Harry Potter","J K Rowling",120));
        Book newBook = new Book(1235,"harry potter","rowling",20);
        given(repository.findAllBytitleContainingIgnoreCase("harry")).willReturn(books);
        List<Book> matchedBooks = repository.findAllBytitleContainingIgnoreCase("harry");
        assertThat(matchedBooks).isNotNull();
    }

    @Test
    public void testtoBuyBook() throws Exception{
        Book newBook = new Book(1235,"Harry Potter","J K Rowling",120);
        given(repository.findByisbn(newBook.getisbn())).willReturn(new Book(1235,"Harry Potter","J K Rowling",120));
        Book book = repository.findByisbn(newBook.getisbn());
        assertThat(book).isNotNull();
    }
}