package com.example.demo.systemUser;

import lombok.Data;

@Data
public class Pagination {
    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private long totalElements;
    private int totalPages;
}