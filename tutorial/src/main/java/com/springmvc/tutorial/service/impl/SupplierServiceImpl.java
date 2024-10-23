package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Province;
import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepositoryCustom;
import com.springmvc.tutorial.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements ISupplierService {

    private final ISupplierRepository repository;

    @Autowired
    public SupplierServiceImpl(ISupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Province> getAllProvince() {
        return this.repository.getAllProvince();
    }

    @Override
    public Optional<Supplier> getDataPage(int PageNumber, int PageSize, String searchValue) {
        return this.repository.getDataPagnationPage(PageNumber, PageSize, searchValue);
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        this.repository.save(supplier);
    }

    @Override
    public Long countSupplier() {
        return this.repository.count();
    }
}
