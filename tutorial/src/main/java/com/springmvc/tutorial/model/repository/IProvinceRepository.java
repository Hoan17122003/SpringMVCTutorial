package com.springmvc.tutorial.model.repository;

import com.springmvc.tutorial.model.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, String> {

}
