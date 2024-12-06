package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Province;
import com.springmvc.tutorial.model.entities.Supplier;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepository;
import com.springmvc.tutorial.model.repository.supplier.ISupplierRepositoryCustom;
import com.springmvc.tutorial.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Supplier> getDataPage(int PageNumber, int PageSize, String searchValue) {
        Pageable pageable = PageRequest.of(PageNumber - 1, PageSize);
        var customer = this.repository.findBySupplierNameContaining(pageable, searchValue);
        return customer.stream().toList();
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        this.repository.save(supplier);
    }

    @Override
    public Long countSupplier(String searchValue) {
        return this.repository.countSupplilerCondition(searchValue);
    }

    @Override
    public Supplier findSupplierById(int id) {
        return this.repository.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void updateSupplier(Supplier supplier, int supplierId) {
        this.repository.findById(supplierId).ifPresent(element -> {
            element = supplier;
            this.repository.save(element);
        });
    }

    @Override
    public void deleteSupplierById(int supplierId) {
        this.repository.deleteById(supplierId);
    }
}
