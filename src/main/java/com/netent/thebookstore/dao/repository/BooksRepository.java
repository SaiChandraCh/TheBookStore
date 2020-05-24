package com.netent.thebookstore.dao.repository;

import com.netent.thebookstore.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    Book findByisbn(int isbn);
    List<Book> findAllBytitleContainingIgnoreCase(String title);
    List<Book> findAllByauthorContainingIgnoreCase(String author);
}
