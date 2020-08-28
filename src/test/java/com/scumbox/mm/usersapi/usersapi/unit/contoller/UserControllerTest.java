package com.scumbox.mm.usersapi.usersapi.unit.contoller;

import com.scumbox.mm.usersapi.usersapi.controller.UserController;
import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.service.PublishService;
import com.scumbox.mm.usersapi.usersapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserControllerTest {
    UserService userService = Mockito.mock(UserService.class);
    PublishService publishService = Mockito.mock(PublishService.class);
    UserController userController = new UserController(userService, publishService);

    @Test
    public void test_findAll_when_has_value() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        List<User> users = new ArrayList<>();
        users.add(user.get());
        Mockito.when(userService.getAll()).thenReturn(users);

        // WHEN
        List<User> result = userController.getAll();

        // THEN
        Assertions.assertTrue(result.size() > 0);
    }


    @Test
    public void test_findByUsername_when_has_value() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(user.get());

        // WHEN
        User result = userController.findByUsername("german");

        // THEN
        Assertions.assertTrue(result.getUsername().equals("german"));
    }

    @Test
    public void test_findByUsername_when_hasNot_value() {
        // GIVEN
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenThrow(NotFoundException.class);

        //WHEN
        try{
            User result = userController.findByUsername("german");

        }catch (NotFoundException nfe) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void test_save_when_is_ok() {
        // GIVEN
        Optional<User> user = Optional.of(new User("german", "12345", "german@test.com"));
        Mockito.when(userService.addUser(Mockito.any())).thenReturn(user.get());
        Mockito.doNothing().when(publishService).notifyUsersCreated(Mockito.any());

        // WHEN
        User result = userController.addUser(user.get());

        // THEN
        // Mockito.verify(publishService).notifyUsersCreated(user.get());
        Assertions.assertTrue(result.getUsername().equals("german"));
    }
}
