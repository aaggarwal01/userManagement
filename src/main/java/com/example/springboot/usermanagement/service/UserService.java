package com.example.springboot.usermanagement.service;

import com.example.springboot.usermanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(String id);

    User save(User theUser);

    void deleteById(String id);

}
