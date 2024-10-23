package com.springmvc.tutorial.model.repository.productphoto;

import com.springmvc.tutorial.model.entities.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductPhotoRepository extends JpaRepository<ProductPhoto, Integer> {
}
