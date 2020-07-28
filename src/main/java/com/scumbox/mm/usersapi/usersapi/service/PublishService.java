package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.commons.QueueConstants;
import com.scumbox.mm.usersapi.usersapi.commons.QueueEvents;
import com.scumbox.mm.usersapi.usersapi.commons.QueueMessage;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

    private static final Logger log = LoggerFactory.getLogger(PublishService.class);


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public PublishService(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notifyUsersCreated(User user) {
        QueueMessage message = new QueueMessage();
        message.setEventName(QueueEvents.USER_CREATED);
        message.setData(user.getId());
        log.info("About to send message: {}" ,user.getId());
        rabbitTemplate.convertAndSend(QueueConstants.EXCHANGE_NAME, QueueConstants.ROUTING_KEY, message);
        log.info("Sent Message");

    }
}
