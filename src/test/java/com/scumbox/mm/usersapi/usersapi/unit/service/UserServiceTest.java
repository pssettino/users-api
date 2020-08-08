package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.UserRepository;
import com.scumbox.mm.usersapi.usersapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserService userService = new UserService(userRepository);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        List<User> users = new ArrayList<>();
        users.add(user.get());

        // WHEN
        Mockito.when(userRepository.findAll()).thenReturn(users);
        List<User> result = userService.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByUsername_when_has_value() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(user);

        // WHEN
        User result = userService.findByUsername("gernan");

        // THEN
        Assertions.assertTrue(result.getUsername().equals("german"));
    }

    @Test
    public void test_findByUsername_when_hasNot_value() {
        // GIVEN
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenThrow(NotFoundException.class);

        // THEN
        try{
            User result = userService.findByUsername("gernan");

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user.get());

        // WHEN
        User result = userService.addUser(user.get());

        // THEN
        Assertions.assertTrue(result.getUsername().equals("german"));
    }

}
