package com.msa.book.application.inputport;

import com.msa.book.application.outputport.BookOutputPort;
import com.msa.book.application.usecase.AddBookUseCase;
import com.msa.book.domain.model.Book;
import com.msa.book.domain.model.vo.BookLocation;
import com.msa.book.domain.model.vo.BookSource;
import com.msa.book.domain.model.vo.Classification;
import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddBookInputPort implements AddBookUseCase {

    private final BookOutputPort bookOutputPort;

    @Override
    public BookOutputDTO addBook(BookInfoDTO bookInfoDTO) {
        Book book = Book.enterBook(
            bookInfoDTO.getTitle(),
            bookInfoDTO.getAuthor(),
            bookInfoDTO.getIsbn(),
            bookInfoDTO.getDescription(),
            bookInfoDTO.getPublicationDate(),
            BookSource.valueOf(bookInfoDTO.getBookSource()),
            Classification.valueOf(bookInfoDTO.getClassification()),
            BookLocation.valueOf(bookInfoDTO.getBookLocation())
        );
        Book save = bookOutputPort.save(book);
        return BookOutputDTO.mapToDTO(save);
    }
}
