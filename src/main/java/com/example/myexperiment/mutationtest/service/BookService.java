package com.example.myexperiment.mutationtest.service;

import com.example.myexperiment.mutationtest.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(long id);
    String addBook(Book book);
    String deleteBookById(long id);
}
