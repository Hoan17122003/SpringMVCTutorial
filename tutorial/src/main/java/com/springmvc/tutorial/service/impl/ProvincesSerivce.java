package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Province;
import com.springmvc.tutorial.model.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesSerivce {

    @Autowired
    private IProvinceRepository repository;

    public List<Province> getAllProvince() {
        return this.repository.findAll().stream().toList();
    }

}
