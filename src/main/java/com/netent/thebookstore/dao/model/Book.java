package com.netent.thebookstore.dao.model;

import javax.persistence.*;
/*
	author: Sai Chandra Chilupui
	last updated date: Sun, 24 May 2020
*/
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "isbn",unique = true)
    private int isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private int price;

    @Column(name = "count")
    private int count;

    public Book() {
        count = 1;
    }

    public Book(int isbn, String title, String author, int price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        count = 1;
    }

    public int getisbn() {
        return isbn;
    }

    public void setisbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
