package com.springmvc.tutorial.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSearchDTO {

    private int orderID;

    private String customerName;

    private LocalDate orderTime;

    private String employeeName;

    private LocalDate acceptTime;

    private String shipperName;

    private LocalDate shippedTime;

    private LocalDate finishedTime;

    private String StatusDescription;
}
