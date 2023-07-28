package com.example.springboot.usermanagement.service;

import com.example.springboot.usermanagement.entity.User;
import com.example.springboot.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }
    @Transactional
    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
