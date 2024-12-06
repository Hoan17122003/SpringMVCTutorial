package com.springmvc.tutorial.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTORequest {

    private Integer productID;
    private Integer amount;

}
