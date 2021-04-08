package com.itbchallenge.eshop.repositories;

import com.itbchallenge.eshop.dtos.UserDTO;

import java.util.ArrayList;

public interface UsersRepository {
    public int addUser(UserDTO user);
    public ArrayList<UserDTO> listAllUsers();

}
