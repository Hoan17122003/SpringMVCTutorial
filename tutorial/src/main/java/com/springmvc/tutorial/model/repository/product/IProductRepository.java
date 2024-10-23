package com.springmvc.tutorial.model.repository.product;

import com.springmvc.tutorial.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
    //native query in JPA and it's support params dynamic
    @Query(
            nativeQuery = true,
            value = "select * from products where ProductID=:productID"
    )
    Product findProductByID(@Param("productID") Integer productID);
}
