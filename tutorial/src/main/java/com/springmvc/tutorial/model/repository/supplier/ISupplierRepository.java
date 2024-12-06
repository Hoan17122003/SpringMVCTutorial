package com.springmvc.tutorial.model.repository.supplier;

import com.springmvc.tutorial.model.entities.Province;
import com.springmvc.tutorial.model.entities.Supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Integer>, ISupplierRepositoryCustom {

    @Query(value = "select * from suppliers where SupplierName=:supplierName", nativeQuery = true)
    public Optional<Supplier> findByName(@Param("supplierName") String supplierName);

    @Query(nativeQuery = true, value = "select * from Province")
    public Optional<Province> getAllProvince();

    @Query(nativeQuery = true, value = "SELECT * FROM suppliers where SupplierName like %:SearchValue% " +
            "ORDER BY supplierID LIMIT :PageSize OFFSET :PageNumber")
    public Optional<Supplier> getDataPagnationPage(@Param("PageNumber") int pageNumber,
                                                   @Param("PageSize") int pageSize,
                                                   @Param("SearchValue") String searchValue);

    public Page<Supplier> findBySupplierNameContaining(Pageable pageable, String searchValue);

    @Query(nativeQuery = true, value = "select COUNT(supplierID) from suppliers where SupplierName like %:searchValue%")
    Long countSupplilerCondition(@Param("searchValue") String searchValue);
}
