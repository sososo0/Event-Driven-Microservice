package com.msa.book;

import com.msa.book.domain.model.Book;
import com.msa.book.domain.model.vo.BookLocation;
import com.msa.book.domain.model.vo.BookSource;
import com.msa.book.domain.model.vo.Classification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) throws Exception {
        domainTest();
        SpringApplication.run(BookApplication.class, args);
    }

    private static void domainTest() {
        System.out.println("------------도메인 모델 테스트 진행------------");
        System.out.println("도서1 입고");

        Book book = Book.enterBook(
            "노인과 바다",
            "허밍웨이",
            "2312321",
            "주인공 노인과 바다",
            LocalDate.now(),
            BookSource.SUPPLY,
            Classification.LITERATURE,
            BookLocation.PANGYO
        );
        System.out.println(book);

        System.out.println("도서1 입고 --> 대여가능 처리");

        book.makeAvailable();
        System.out.println(book.getBookStatus());

        System.out.println("샘플도서 생성");

        Book sample = Book.sample();
        System.out.println(sample);
        sample.makeUnAvailable();
        System.out.println(sample.getBookStatus());
    }
}
