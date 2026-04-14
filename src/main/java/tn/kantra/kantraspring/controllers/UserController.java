package tn.kantra.kantraspring.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {


    UserService userService;
    public UserController (UserService us){
        userService=us;
    }

    @PostMapping("addUser")
    public UserEntity addUser(@RequestBody UserEntity u){
       return userService.addUser(u);
    }
}
