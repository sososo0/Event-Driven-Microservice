package com.msa.book.framework.web.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class BookInfoDTO {

    private String title;
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String bookSource;
    private String classification;
    private String bookLocation;
}
