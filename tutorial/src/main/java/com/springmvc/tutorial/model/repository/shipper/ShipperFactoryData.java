package com.springmvc.tutorial.model.repository.shipper;

import com.springmvc.tutorial.model.entities.Shipper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShipperFactoryData {
    String urlData = "src/main/java/datawithdb/shipper.txt";

    public List<Shipper> getDataWithFile() throws IOException {
        List<Shipper> shippers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(this.urlData);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            shippers.add(new Shipper(line[0], line[1]));
        }
        return shippers;
    }
}
