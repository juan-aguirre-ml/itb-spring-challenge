package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesDTO {
    private ArrayList<TicketProductDTO> articles;
}
