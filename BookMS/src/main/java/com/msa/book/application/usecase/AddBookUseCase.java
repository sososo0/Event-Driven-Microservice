package com.msa.book.application.usecase;

import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutputDTO;

public interface AddBookUseCase {
    public BookOutputDTO addBook(BookInfoDTO bookInfoDTO);
}
