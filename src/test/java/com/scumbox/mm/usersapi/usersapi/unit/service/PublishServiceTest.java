package com.scumbox.mm.usersapi.usersapi.unit.service;

import com.scumbox.mm.usersapi.usersapi.commons.QueueConstants;
import com.scumbox.mm.usersapi.usersapi.commons.QueueMessage;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import com.scumbox.mm.usersapi.usersapi.service.PublishService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

public class PublishServiceTest {
    RabbitTemplate rabbitTemplate = Mockito.mock(RabbitTemplate.class);
    PublishService publishService = new PublishService(rabbitTemplate);


    @Test
    public void test_notify_when_is_ok() {
        // WHEN
        publishService.notifyUsersCreated(new User());

        // THEN
        Mockito.verify(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(QueueMessage.class));
    }

}
