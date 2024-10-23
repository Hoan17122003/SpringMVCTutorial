package com.springmvc.tutorial.model.repository.supplier.impl;

import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.lang.Double;

@Transactional
public class SupplierRepository implements ISupplierRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


//    @Override
//    public Supplier getSupplierByName(String supplierName) {
//
//        String query = """
//                select * from suppliers where SupplierName like :supplierName
//                """;
//        return (Supplier) entityManager.createQuery(query)
//                .setParameter("supplierName", new StringBuilder().append("%").append(supplierName).append("%").toString())
//                .getSingleResult();
//    }




    public void insertDataWithFile(List<Supplier> entity) {

    }
}
