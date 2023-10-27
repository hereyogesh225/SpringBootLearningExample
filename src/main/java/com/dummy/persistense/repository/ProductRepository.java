package com.dummy.persistense.repository;

import com.dummy.persistense.entity.ProductHE;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductHE, Integer> {
    List<ProductHE> findAllByCategory(String category);

    List<ProductHE> deleteByCategory(String category);

    List<ProductHE> removeByBrand(String brand);

    List<ProductHE> findByPriceBetween(Integer from, Integer to);

    List<ProductHE> findByPriceLessThan(Integer price);

    List<ProductHE> findByPriceLessThanEqual(Integer price);

    List<ProductHE> findByPriceGreaterThan(Integer price);

    List<ProductHE> findByPriceGreaterThanEqual(Integer price);

    List<ProductHE> findByPriceIn(Collection<Integer> prices);

    List<ProductHE> findByTitleStartingWith(String title);

    List<ProductHE> findByTitleEndingWith(String title);

    List<ProductHE> findByTitleContaining(String title);
}
