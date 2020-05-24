package com.netent.thebookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.services.BookStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

/*
	author: Sai Chandra Chilupui
	last updated date: Sun, 24 May 2020
*/

@RunWith(MockitoJUnitRunner.class)
public class BookStoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookStoreService service;

    @InjectMocks
    private BookStoreController bookStoreController;

    private List<Book> books;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        books.add(new Book(1235,"Harry Potter","J K Rowling",120));
        books.add(new Book(2213,"2 States","Chetan Bhagath",100));
        books.add(new Book(3289,"BatMan","Christopher Nolan",150));;
        mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
    }

    @Test
    public void addBook() throws Exception {
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(post("/book-store/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isCreated());
    }

    @Test
    public void searchByIsbn() throws Exception {
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(get("/book-store/searchbyisbn/1235")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void searchByTitle() throws Exception{
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(get("/book-store/searchbytitle/Harry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isFound());
    }


    @Test
    public void searchByAuthor() throws Exception{
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(get("/book-store/searchbyauthor/Rowling")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isFound());
    }

    @Test
    public void searchInCoverage() throws Exception{
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(get("/book-store/searchcoverage/1235")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void buyBook() throws Exception{
        Book book = new Book(1235,"Harry Potter","J K Rowling",120);
        mockMvc.perform(get("/book-store/searchcoverage/1235")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }


    @Test
    public void getBooksMatchedWithCoverage(){
        Book book = new Book(1235,"sunt aut facere","J K Rowling",20);
        List<String> expectedPosts = new ArrayList<>();
        expectedPosts.add("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        given(service.getBooksMatchedWithCoverage(1235)).willReturn(expectedPosts);
        List<String> posts = service.getBooksMatchedWithCoverage(1235);
        Assert.assertTrue(posts.size()>0);
    }
}