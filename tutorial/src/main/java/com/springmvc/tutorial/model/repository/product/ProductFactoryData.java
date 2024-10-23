package com.springmvc.tutorial.model.repository.product;

import com.springmvc.tutorial.model.entities.Product;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductFactoryData {
    private final String urlData = "src/main/java/datawithdb/products.txt";

    public List<Product> getDataWithFile() throws IOException {
        List<Product> products = new ArrayList<>();
        FileInputStream fis = new FileInputStream(this.urlData);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            Product product = new Product();
            product.setProductName(line[0]);
            product.setProductDescription("");
            product.setSupplierID(Integer.parseInt(line[1]));
            product.setCategoryID(Integer.parseInt(line[2]));
            product.setUnit(line[3].toString());
//            product.setUnit("cái");
            product.setPrice(Double.parseDouble(line[4]));
            product.setPhoto(" ");
            product.setSelling(Boolean.parseBoolean(line[5]));
            products.add(product);
        }
        return products;
    }
}
