package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO {
    private TicketDTO ticket;
    private StatusCodeDTO statusCode;
}
