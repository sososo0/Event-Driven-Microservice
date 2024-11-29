package com.msa.bestbook.persistence;

import com.msa.bestbook.domain.model.BestBook;
import com.msa.bestbook.domain.model.vo.BookItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestBookRepository extends MongoRepository<BestBook, String> {
    public BestBook findBestBookByItem(BookItem item);
}
