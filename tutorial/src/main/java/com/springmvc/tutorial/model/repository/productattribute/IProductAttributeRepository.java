package com.springmvc.tutorial.model.repository.productattribute;

import com.springmvc.tutorial.model.entities.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
