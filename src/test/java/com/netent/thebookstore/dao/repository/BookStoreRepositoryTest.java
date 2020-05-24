package com.netent.thebookstore.dao.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.netent.thebookstore.dao.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-books-datasets.xml")
public class BookStoreRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BooksRepository repository;

    @Test
    public void testtoFindAllByisbn() {
        // Find an inserted record
        Book foundBook = repository.findByisbn(10);
        assertThat(foundBook.getTitle(), is(equalTo("Harry Potter")));
    }

    @Test
    public void testtoFindAllByTitle() {
        List<Book> foundBooks = repository.findAllBytitleContainingIgnoreCase("Harry");
        assertThat(foundBooks.get(0).getTitle(), is(equalTo("Harry Potter")));
    }

    @Test
    public void testtoFindAllByAuthor() {
        List<Book> foundBooks = repository.findAllByauthorContainingIgnoreCase("Rowling");
        assertThat(foundBooks.get(0).getTitle(), is(equalTo("Harry Potter")));
    }
}
