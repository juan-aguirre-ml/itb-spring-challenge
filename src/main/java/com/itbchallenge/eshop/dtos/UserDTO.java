package com.itbchallenge.eshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String state;

}
