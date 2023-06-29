package com.example.myexperiment.mutationtest.service;

import com.example.myexperiment.mutationtest.entity.Book;
import com.example.myexperiment.mutationtest.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testGetBooks() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Unknown Author").year(2023).build();
        bookService.addBook(book);
        List<Book> result = bookService.getBooks();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(List.of(book), result);
    }

    @Test
    public void testGetBookById_case1() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Unknown Author").year(2023).build();
        bookService.addBook(book);
        Book result = bookService.getBookById(book.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result);
    }

    @Test
    public void testGetBookById_case2() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Unknown Author").year(2023).build();
        bookService.addBook(book);
        Book result = bookService.getBookById(12);

        Assertions.assertNull(result);
    }

    @Test
    public void testAddBook() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Unknown Author").year(2023).build();
        String result = bookService.addBook(book);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("CREATED", result);
        Assertions.assertEquals(1, bookService.getBooks().size());
    }

    @Test
    public void testDeleteBookById_case1() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Delvian").year(2023).build();
        bookService.addBook(book);
        String result = bookService.deleteBookById(book.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("DELETED", result);
        Assertions.assertEquals(0, bookService.getBooks().size());
    }

    @Test
    public void testDeleteBookById_case2() {
        Book book = Book.builder().id(0).title("Mutation Test").author("Delvian").year(2023).build();
        bookService.addBook(book);
        String result = bookService.deleteBookById(12);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("DELETED", result);
        Assertions.assertEquals(1, bookService.getBooks().size());
    }
}
