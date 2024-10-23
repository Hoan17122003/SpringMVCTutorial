package com.springmvc.tutorial.model.repository.category;

import com.springmvc.tutorial.model.entities.Category;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryFactoryData {
    String urlData = "src/main/java/datawithdb/categories.txt";

    public List<Category> getDataWithFile() throws IOException {
        FileInputStream fis = new FileInputStream(urlData);
        Scanner sc = new Scanner(fis);
        List<Category> categories = new ArrayList<Category>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().toString().split("@");
            Category category = new Category();
            category.setCategoryName(line[1].toString());
            category.setDescription(line[2].toString());
            categories.add(category);
        }
        sc.close();
        fis.close();
        return categories;
    }
}
