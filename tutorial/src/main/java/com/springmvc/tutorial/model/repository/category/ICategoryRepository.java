package com.springmvc.tutorial.model.repository.category;

import com.springmvc.tutorial.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>, ICategoryRepositoryCustom {

    @Modifying
    @Query(nativeQuery = true, value = """
                                         SELECT *
                                         FROM
                                         (
             	                            SELECT *, ROW_NUMBER() OVER (ORDER BY CategoryName) AS RowNumber
             	                            FROM categories
             	                            WHERE CategoryName like :searchValue
                                         ) AS t
                                         WHERE	:PAGE_SIZE = 0
             		                            OR RowNumber BETWEEN (:page - 1) * :PAGE_SIZE + 1 AND :page * :PAGE_SIZE
                                         ORDER BY RowNumber
            
            """)
    List<Category> ListData(@Param("page") Integer page, @Param("PAGE_SIZE") Integer PAGE_SIZE, @Param("searchValue") String searchValue);

    // Tìm kiếm Category theo tên có hỗ trợ phân trang
    Page<Category> findByCategoryNameContaining(String searchValue, Pageable pageable);

    // Tìm kiếm category theo tên có hỗ trợ phân trang tương tự như hàm trên
    @Query(nativeQuery = true, value = """
                select * from categories where CategoryName like %:searchValue%
            """)
    List<Category> findByCategoryName(@Param("searchValue") String searchValue, Pageable pageable);
}
