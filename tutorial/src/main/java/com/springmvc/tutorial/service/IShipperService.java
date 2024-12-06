package com.springmvc.tutorial.service;

import com.springmvc.tutorial.model.entities.Shipper;

import java.util.*;

public interface IShipperService {
    public List<Shipper> getPaganation(int page, int PAGE_SIZE, String searchValue);

    public void saveShipper(Shipper shipper);

    public void deleteShipper(int shipperId);

    public void editShipper(Shipper shipper, int shipperId);

    public Long countShipperByCondition(String searchValue);

    public Shipper findById(int shipperId);
}
