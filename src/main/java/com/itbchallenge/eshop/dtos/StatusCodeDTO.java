package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusCodeDTO {
    private HttpStatus code;
    private String message;
}
