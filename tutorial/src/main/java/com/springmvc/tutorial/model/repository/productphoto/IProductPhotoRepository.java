package com.springmvc.tutorial.model.repository.productphoto;

import com.springmvc.tutorial.model.entities.ProductPhoto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductPhotoRepository extends JpaRepository<ProductPhoto, Integer> {

    public Optional<ProductPhoto> findByProductIDAndPhotoId(int productId, int productPhotoId);

    public void deleteByProductID(int productId);

    @Query(value = """
                select * from productphotos where productID = :productID
            """, nativeQuery = true)
    List<ProductPhoto> findByProductID(@Param("productID") int productID);
}
