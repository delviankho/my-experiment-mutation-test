package com.example.myexperiment.mutationtest.service.impl;

import com.example.myexperiment.mutationtest.entity.Book;
import com.example.myexperiment.mutationtest.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private List<Book> bookList = new ArrayList<>();

    @Override
    public List<Book> getBooks() {
        return bookList;
    }

    @Override
    public Book getBookById(long id) {
        return bookList.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    @Override
    public String addBook(Book book) {
        bookList.add(book);

        return "CREATED";
    }

    @Override
    public String deleteBookById(long id) {
        bookList.removeIf(book -> book.getId() == id);

        return "DELETED";
    }
}
