package com.msa.book.domain.model;

import com.msa.book.domain.model.vo.BookDesc;
import com.msa.book.domain.model.vo.BookLocation;
import com.msa.book.domain.model.vo.BookSource;
import com.msa.book.domain.model.vo.BookStatus;
import com.msa.book.domain.model.vo.Classification;
import java.time.LocalDate;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long no;
    private String title;

    @Embedded
    private BookDesc bookDesc;
    private Classification classification;
    private BookStatus bookStatus;
    private BookLocation location;

    public static Book enterBook(
        String title,
        String author,
        String isbn,
        String description,
        LocalDate publicationDate,
        BookSource bookSource,
        Classification classification,
        BookLocation bookLocation
    ) {
        BookDesc desc = BookDesc.createBookDesc(
            description, author, isbn, publicationDate, bookSource
        );
        Book book = new Book();
        book.setTitle(title);
        book.setBookDesc(desc);
        book.setClassification(classification);
        book.setLocation(bookLocation);
        book.setBookStatus(BookStatus.ENTERED);
        return book;
    }

    public static Book sample() {
        return enterBook(
            "엔터프라이즈 아키텍처 패턴",
            "마틴파울러",
            "21321321",
            "엔터프라이즈 패턴에 관한 좋은 서적",
            LocalDate.now(),
            BookSource.SUPPLY,
            Classification.COMPUTER,
            BookLocation.JEONGJA
        );
    }

    public Book makeAvailable() {
        this.setBookStatus(BookStatus.AVAILABLE);
        return this;
    }

    public Book makeUnAvailable() {
        this.setBookStatus(BookStatus.UNAVAILABLE);
        return this;
    }
}
