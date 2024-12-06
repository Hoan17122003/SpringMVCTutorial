package com.springmvc.tutorial.model.repository.product;

import com.springmvc.tutorial.model.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
    // native query in JPA and it's support params dynamic
    // @Query(
    // nativeQuery = true,
    // value = "select * from products where ProductID=:productID"
    // )
    // Product findProductByID(@Param("productID") Integer productID);

    @Transactional
    @Query(nativeQuery = true, value = """
                select COUNT(ProductID) from products where ProductName like %:searchValue% or :searchValue = N''
            """)
    Long countByProductName(@Param("searchValue") String searchValue);

    @Modifying
    @Query(nativeQuery = true, value = """
                                         SELECT *
                                         FROM
                                         (   SELECT *, ROW_NUMBER() OVER (ORDER BY ProductName) AS RowNumber
                                             FROM products
             	                             WHERE   (:searchValue = N'' OR ProductName like %:searchValue%)
                                            				                            AND (:categoryId = 0 OR CategoryID =:categoryId)
                                            				                            AND (:supplierId = 0 OR SupplierID =:supplierId)
                                            				                            AND (Price >= :minPrice)
                                            				                            AND (:maxPrice <= 0 OR Price <= :maxPrice)
                                         ) AS t
                                         WHERE	:PAGESIZE = 0
             		                            OR RowNumber BETWEEN (:page - 1) * :PAGESIZE + 1 AND :page * :PAGESIZE
                                         ORDER BY RowNumber

            """)
    public List<Product> findByCondition(@Param("page") int page, @Param("PAGESIZE") int PAGE_SIZE,
            @Param("searchValue") String searchValue, @Param("supplierId") int supplierId,
            @Param("categoryId") int categoryId, @Param("minPrice") float minPrice,
            @Param("maxPrice") float maxPrice);

    // public Page<Product>
    // findByProductNameAndSupplierIDAndCategoryIDAndPriceBetweenContaining(Pageable
    // pageable, String searchValue, int SupplierId, int categoryId, float minPrice,
    // float maxPrice);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);
}
