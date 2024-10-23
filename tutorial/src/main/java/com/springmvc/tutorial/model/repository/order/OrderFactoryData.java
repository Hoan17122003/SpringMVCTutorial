package com.springmvc.tutorial.model.repository.order;

import com.springmvc.tutorial.model.entities.Order;
import com.springmvc.tutorial.model.entities.OrderStatus;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderFactoryData {
    String urlData = "src/main/java/datawithdb/order.txt";
    String urlDataOrderStatus = "src/main/java/datawithdb/orderstatus.txt";

    public List<Order> getDataWithFileOrder() throws IOException {
        List<Order> orders = new ArrayList<Order>();
        FileInputStream fis = new FileInputStream(this.urlData);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            Order order = new Order();
            order.setCustomerID(Integer.parseInt(line[0]));
            order.setOrderTime(LocalDateTime.parse(line[1]));
            order.setDeliveryProvince(line[2]);
            order.setDeliveryAddress(line[3]);
            order.setEmployeeID(Integer.parseInt(line[4]));
            order.setAcceptTime(LocalDateTime.parse(line[5]));
            order.setShipperID(Integer.parseInt(line[6]));
            order.setShippedTime(LocalDateTime.parse(line[7]));
            order.setFinishedTime(LocalDateTime.parse(line[8]));
            orders.add(order);
        }
        return orders;
    }

    public List<OrderStatus> getDataWithFileOrderStatus() throws IOException {
        List<OrderStatus> orderStatus = new ArrayList<>();
        FileInputStream fis = new FileInputStream(this.urlDataOrderStatus);
        Scanner sc = new Scanner(fis);
        while (sc.hasNextLine()) {
            String line[] = sc.nextLine().split(";");
            orderStatus.add(new OrderStatus(Integer.parseInt(line[0]), line[1]));
        }
        return orderStatus;
    }
}
