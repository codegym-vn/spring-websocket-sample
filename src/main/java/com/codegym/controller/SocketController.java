package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/user/{name}")
    //@SendTo("/topic/users")
    public void showUsers(@DestinationVariable String name) {
        System.out.println("Getting " + name);
        List<User> users = new ArrayList<User>();
        if (name != null) {
            User user = new User();
            user.setName(name);
            users.add(user);
        }
        simpMessagingTemplate.convertAndSend("/topic/users", users);
        //return users;
    }
}
