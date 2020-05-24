package com.netent.thebookstore.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.netent.thebookstore.dao.model.Book;
import com.netent.thebookstore.dao.repository.BooksRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
	author: Sai Chandra Chilupui
	last updated date: Sun, 24 May 2020
*/
/*
    This service maintains relation between api and DAO's
* */
@Service
public class BookStoreService {

    @Autowired
    BooksRepository booksRepository;

    private List<JSONObject> posts = new ArrayList<>();
    /*persists book object in DB if requirments are met*/
    public Book addBook(Book newBook) throws Exception {
        if (newBook.getTitle() == null || newBook.getAuthor() == null
                || newBook.getTitle().isEmpty() || newBook.getAuthor().isEmpty()
                || newBook.getPrice() <= 0){
            throw new Exception("Missing or Invalid Parameters");
        }
        Book existingBook = booksRepository.findByisbn(newBook.getisbn());
        if(existingBook != null){
            if(!existingBook.getTitle().equals(newBook.getTitle()) || !existingBook.getAuthor().equals(newBook.getAuthor())
                    || existingBook.getPrice() != newBook.getPrice()){
                throw new Exception("ISBN is associated with existing book, please change the ISBN");
            }
            newBook.setCount(newBook.getCount()+1);
        }
        return booksRepository.save(newBook);
    }

    /*searches for the book with given isbn*/
    public Book getBookByIsbn(int isbn){
        return booksRepository.findByisbn(isbn);
    }

    /*searches for the book conatining given title*/
    public List<Book> getBooksByTitle(String title){
        return booksRepository.findAllBytitleContainingIgnoreCase(title);
    }

    /*searches for the book conatining given author*/
    public List<Book> getBooksByAuthor(String author){
        return booksRepository.findAllByauthorContainingIgnoreCase(author);
    }

    /* Fetches a stores coverage from https://jsonplaceholder.typicode.com/posts */
    @Bean
    public void getCoverageCache(){
        try {
            HttpResponse<JsonNode> jsonResponse
                    = Unirest.get("https://jsonplaceholder.typicode.com/posts")
                    .header("accept", "application/json").asJson();
            for (int i = 0; i < jsonResponse.getBody().getArray().length() ; i++) {
                posts.add(jsonResponse.getBody().getArray().getJSONObject(i));
            }
        } catch (UnirestException | JSONException e) {
            e.printStackTrace();
        }
    }

    /*searches for the book with given isbn in the coverage*/
    public List<String> getBooksMatchedWithCoverage(int isbn) {
        Book book = getBookByIsbn(isbn);
        List<String> matchedPosts = new ArrayList<>();
        for(int i = 0; i < posts.size(); i++) {
            String title = null,author = null;
            try {
                title = (String)posts.get(i).get("title");
                author = (String)posts.get(i).get("body");
                if((title.contains(book.getTitle())) || (author.contains(book.getTitle()))){
                    matchedPosts.add((String) posts.get(i).get("title"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return matchedPosts;
    }

    /*searches the book in DB, returns if available else adds to the db. This method also maintains the count*/
    public Book buyBook(Book book) throws Exception {
        Book matchedBook = booksRepository.findByisbn(book.getisbn());
        if(matchedBook == null) {
            matchedBook = book;
            addBook(matchedBook);
            matchedBook.setCount(0);
            booksRepository.save(matchedBook);
        }
        if(matchedBook.getCount() == 0){
            throw new Exception("Requested book is unavailable");
        }
        matchedBook.setCount(matchedBook.getCount()-1);
        return booksRepository.save(matchedBook);
    }
}
