package com.msa.book.framework.jpaadapter;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookAdapter implements BookOutputPort {

    private final BookRepository bookRepository;

    @Override
    public Book loadBook(long bookNo) {
        return bookRepository.findById(bookNo).get();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
