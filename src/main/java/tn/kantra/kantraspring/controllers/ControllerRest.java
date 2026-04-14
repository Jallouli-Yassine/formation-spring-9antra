package tn.kantra.kantraspring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest1")
public class ControllerRest {

    @GetMapping("test")
    public String test(){
        return "hello 9antra";
    }

    @GetMapping("test2")
    public String test2(){
        return "hello 9antra 2";
    }
}