package com.itbchallenge.eshop.repositories;


import com.itbchallenge.eshop.dtos.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UsersRepositoryImple implements UsersRepository{

    private AtomicInteger userIdGen = new AtomicInteger();
    private HashMap<Integer,UserDTO> userRepo = new HashMap<>();

    @Override
    public int addUser(UserDTO user) {
        //Todo: Validate user data
        this.userRepo.put(this.userIdGen.incrementAndGet(),user);
        return this.userIdGen.get();
    }

    @Override
    public ArrayList<UserDTO> listAllUsers() {
        return new ArrayList<UserDTO>(this.userRepo.values());
    }
}
