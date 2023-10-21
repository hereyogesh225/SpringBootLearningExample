package com.dummy.persistense.repository;

import com.dummy.persistense.entity.QuotesHE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotesRepository extends JpaRepository<QuotesHE, Integer> {
}
