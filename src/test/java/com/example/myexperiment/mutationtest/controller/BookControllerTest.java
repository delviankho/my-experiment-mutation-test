package com.example.myexperiment.mutationtest.controller;

import com.example.myexperiment.mutationtest.entity.Book;
import com.example.myexperiment.mutationtest.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {BookController.class})
@ActiveProfiles(profiles = "unit-test")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetBooks() throws Exception {
        // given
        List<Book> bookList = new ArrayList<>();
        bookList.add(Book.builder().id(1).title("Mutation Test").author("Unknown Author").year(2023).build());
        bookList.add(Book.builder().id(2).title("Java Programming").author("James Gosling").year(2023).build());
        when(bookService.getBooks()).thenReturn(bookList);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Mutation Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("Unknown Author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Java Programming"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].author").value("James Gosling"))
                .andReturn();
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = Book.builder().id(1).title("Mutation Test").author("Unknown Author").year(2023).build();
        when(bookService.getBookById(1)).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Mutation Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Unknown Author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2023));
    }

    @Test
    public void testAddBook() throws Exception {
        String result = "CREATED";
        when(bookService.addBook(any())).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"title\": \"Mutation Test\", \"author\": \"Unknown Author\", \"year\": 2023}"))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    public void testDeleteBookById() throws Exception {
        String result = "DELETED";
        when(bookService.deleteBookById(1)).thenReturn(result);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }
}
