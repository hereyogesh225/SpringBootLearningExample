package com.dummy.persistense.repository;

import com.dummy.persistense.entity.QuotesHE;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotesRepository extends JpaRepository<QuotesHE, Integer> {

    List<QuotesHE> findAllByOrderByQuoteAsc();

    List<QuotesHE> findByAuthorOrderByQuoteAsc(String author);

    List<QuotesHE> findByAuthor(String author, Sort sort);

    List<QuotesHE> findFirst3ByOrderByQuoteDesc();
}
