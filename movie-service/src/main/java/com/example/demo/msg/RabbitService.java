package com.example.demo.msg;

import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MovieMessage movieMsg) {
        rabbitTemplate.convertAndSend("movies", movieMsg.getMessage());
    }
}
