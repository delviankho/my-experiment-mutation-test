package com.example.myexperiment.mutationtest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private long id;
    private String title;
    private String author;
    private int year;
}
