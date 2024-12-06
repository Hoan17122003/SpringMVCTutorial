package com.springmvc.tutorial.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO implements Serializable {

    private final Long serializableCartID = 1L;

    private Integer productID;
    private String productName;
    private int amount;
    private String productPhoto;
    private Double salePrice;

}
