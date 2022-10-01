package com.example.demo.msg;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(SerieMessage serieMsg) {
        rabbitTemplate.convertAndSend("series", serieMsg.getMessage());
    }
}
