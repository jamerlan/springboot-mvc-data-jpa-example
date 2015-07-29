package com.example.myproject.service;

import com.example.myproject.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}