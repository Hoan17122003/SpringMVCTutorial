package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Product;
import com.springmvc.tutorial.model.entities.Province;
import com.springmvc.tutorial.model.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    public List<Supplier> getAllSupplier();

    public Optional<Province> getAllProvince();

    public Optional<Supplier> getDataPage(int PageNumber, int PageSize, String searchValue);

    public void saveSupplier(Supplier supplier);

    public Long countSupplier();
}
