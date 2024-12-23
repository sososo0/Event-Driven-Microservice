package com.msa.book.application.outputport;

import com.msa.book.domain.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOutputPort {
    public Book loadBook(long bookNo);
    public Book save(Book book);
}
