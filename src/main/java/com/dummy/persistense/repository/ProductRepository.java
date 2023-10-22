package com.dummy.persistense.repository;

import com.dummy.persistense.entity.ProductHE;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductHE, Integer> {
    List<ProductHE> findAllByCategory(String category);

    List<ProductHE> deleteByCategory(String category);

    List<ProductHE> removeByBrand(String brand);
}
