package com.springmvc.tutorial.model.repository.supplier;

import com.springmvc.tutorial.model.entities.Supplier;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierFactoryData {
    String urlData = "src/main/java/datawithdb/suppliers.txt";

    public List<Supplier> getDataWithFile() throws IOException {
        List<Supplier> suppliers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(this.urlData);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            Supplier supplier = new Supplier();
            supplier.setSupplierName(line[0]);
            supplier.setContactName(line[1]);
            supplier.setProvince(line[2]);
            supplier.setAddress(line[3]);
            supplier.setPhone(line[4]);
            supplier.setEmail(line[5]);
            supplier.setLogo(null);
            suppliers.add(supplier);
        }
        return suppliers;
    }
}
