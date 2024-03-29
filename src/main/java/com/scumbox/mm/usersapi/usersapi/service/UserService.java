package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict(value = "users", allEntries = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @CachePut(value = "users")
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Cacheable(value = "users")
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.orElseThrow(NotFoundException::new);
    }
}
