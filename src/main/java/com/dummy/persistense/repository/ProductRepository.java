package com.dummy.persistense.repository;

import com.dummy.persistense.entity.ProductHE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductHE,Integer> {
}
