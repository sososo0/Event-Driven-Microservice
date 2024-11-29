package com.msa.book.framework.web.controller;

import com.msa.book.application.usecase.AddBookUseCase;
import com.msa.book.application.usecase.InquiryUseCase;
import com.msa.book.framework.web.dto.BookInfoDTO;
import com.msa.book.framework.web.dto.BookOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final AddBookUseCase addBookUseCase;
    private final InquiryUseCase inquiryUseCase;

    @PostMapping("/book")
    public ResponseEntity<BookOutputDTO> createBook(
        @RequestBody BookInfoDTO bookInfoDTO
    ) {
        BookOutputDTO bookOutPutDTO = addBookUseCase.addBook(bookInfoDTO);
        return new ResponseEntity<>(bookOutPutDTO, HttpStatus.CREATED);
    }

    @GetMapping("/book/{no}")
    public ResponseEntity<BookOutputDTO> getBookInfo(
        @PathVariable("no") String no
    ) {
        BookOutputDTO bookInfo = inquiryUseCase.getBookInfo(Long.parseLong(no));
        return bookInfo != null ? new ResponseEntity<>(bookInfo, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
