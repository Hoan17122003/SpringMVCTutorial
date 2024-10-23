package com.springmvc.tutorial.model.repository.category;

import com.springmvc.tutorial.model.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Transactional
public class CategoryRepositoryImpl implements ICategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


//    @Override
//    public String insertDataWithFile(List<Category> entity) {
////        entityManager = getEntityManager();
//        entityManager.getTransaction().begin();
//        entity.stream().forEach(element -> {
//            entityManager.persist(element);
//            entityManager.getTransaction().commit();
//        });
//        return "import categories data";
//    }
}
