package com.dummy.persistense.repository;

import com.dummy.persistense.entity.QuotesHE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotesRepository extends JpaRepository<QuotesHE, Integer> {
}
