package com.msa.bestbook.domain.model;

import com.msa.bestbook.domain.model.vo.BookItem;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class BestBook {

    @Id
    private String id;
    private BookItem item;
    private long rentCount;

    public static BestBook registerBestBook(BookItem item) {
        UUID uuid = UUID.randomUUID();
        var bestBook = new BestBook();
        bestBook.setId(uuid.toString());
        bestBook.setItem(item);
        bestBook.setRentCount(1L);
        return bestBook;
    }

    public Long increaseBestBookCount() {
        this.rentCount = this.getRentCount() + 1;
        return this.rentCount;
    }
}
