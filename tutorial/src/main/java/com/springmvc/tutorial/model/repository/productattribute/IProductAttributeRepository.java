package com.springmvc.tutorial.model.repository.productattribute;

import com.springmvc.tutorial.model.entities.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface IProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {

    @Query(nativeQuery = true, value = """
            select * from productattributes
            where AttributeID = :productAttributeId and productID = :productId
            """)
    Optional<ProductAttribute> findByIdAndProductID(@Param("productAttributeId") int productAttributeId,
            @Param("productId") int productId);

    void deleteByProductID(int productId);

    Optional<List<ProductAttribute>> findByProductID(int productId);
}
