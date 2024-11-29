package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.application.usecase.MakeAvailableUseCase;
import com.msa.book.domain.model.Book;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MakeAvailableInputPort implements MakeAvailableUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO available(Long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo);
        loadBook.makeAvailable();
        return BookOutputDTO.mapToDTO(loadBook);
    }
}
