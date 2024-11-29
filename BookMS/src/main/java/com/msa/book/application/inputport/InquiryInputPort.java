package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.application.usecase.InquiryUseCase;
import com.msa.book.domain.model.Book;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryInputPort implements InquiryUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO getBookInfo(long bookNo) {
        Book loadBook = bookOutputPort.loadBook(bookNo);
        return BookOutputDTO.mapToDTO(loadBook);
    }
}
