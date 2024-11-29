package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.application.usecase.MakeUnAvailableUseCase;
import com.msa.book.domain.model.Book;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeUnAvailableInputPort implements MakeUnAvailableUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO unAvailable(long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo);
        loadBook.makeUnAvailable();
        return BookOutputDTO.mapToDTO(loadBook);
    }
}
