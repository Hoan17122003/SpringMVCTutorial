package com.springmvc.tutorial.service.impl;

import com.springmvc.tutorial.model.entities.Shipper;
import com.springmvc.tutorial.model.repository.shipper.IShipperRepository;
import com.springmvc.tutorial.service.IShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements IShipperService {

    @Autowired
    private IShipperRepository repository;

    @Override
    public List<Shipper> getPaganation(int page, int PAGE_SIZE, String searchValue) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE);
        var result = this.repository.findByShipperNameContaining(pageable, searchValue);
        return result.stream().toList();
    }

    @Override
    public Long countShipperByCondition(String searchValue) {
        return this.repository.countDistinctByShipperName(searchValue);
    }

    @Override
    public void deleteShipper(int shipperId) {
        this.repository.deleteById(shipperId);
    }

    @Override
    public void editShipper(Shipper shipper, int shipperId) {
        if (shipper == null) return;
        this.repository.findById(shipperId).ifPresent(element -> {
            if (element.getPhone().equals(shipper.getPhone())) {
                element.setShipperName(shipper.getShipperName());
            } else {
                element = shipper;
            }
            this.repository.save(element);
        });
    }

    @Override
    public void saveShipper(Shipper shipper) {
        this.repository.save(shipper);
    }

    @Override
    public Shipper findById(int shipperId) {
        return this.repository.findById(shipperId).stream().findFirst().orElse(null);
    }
}
