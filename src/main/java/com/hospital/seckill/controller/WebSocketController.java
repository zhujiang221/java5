package com.hospital.seckill.controller;


import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
    @RequestMapping("/receive_msg")
    @SendTo("/topic/warn")
    public String handleMessage(String message) {
        return message;
    }
}
