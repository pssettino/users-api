package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.UserNotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findByName(String name) {
        Optional<User> user = userRepository.findByName(name);

        return user.orElseThrow(UserNotFoundException::new);
    }
}
