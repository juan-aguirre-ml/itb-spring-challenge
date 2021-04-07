package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketProductDTO {
    private int productId;
    private String name;
    private String brand;
    private int quantity;
}
