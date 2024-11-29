package com.msa.book.domain.model.vo;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookDesc {
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private BookSource source;

    public static BookDesc createBookDesc(
        String description,
        String author,
        String isbn,
        LocalDate publicationDate,
        BookSource source
    ) {
        return new BookDesc(description, author, isbn, publicationDate, source);
    }

    public static BookDesc sample() {
        return createBookDesc("엔터프라이즈 아키텍처 패턴을 설명", "마틴파울러", "12345678", LocalDate.now(), BookSource.SUPPLY);
    }
}
