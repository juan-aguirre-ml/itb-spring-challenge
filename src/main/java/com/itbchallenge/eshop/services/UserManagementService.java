package com.itbchallenge.eshop.services;

import com.itbchallenge.eshop.dtos.UserDTO;

import java.util.ArrayList;

public interface UserManagementService {
    public ArrayList<UserDTO> getAllUsers();

    public UserDTO addUser();

    public ArrayList<UserDTO> getUsersBy(String param, String value);



}
