package com.msa.bestbook.domain.model.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookItem implements Serializable {

    private Integer no;
    private String title;

    public static BookItem sample() {
        return new BookItem(10, "도메인주도로 시작하는 마이크로서비스 개발");
    }

    public static BookItem sample2() {
        return new BookItem(20, "경험과 사례로 풀어낸 성공하는 애자일");
    }
}
