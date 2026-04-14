package tn.kantra.kantraspring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest2")
public class ControllerRest2 {

    @GetMapping("test")
    public String test(){
        return "hello 9antra";
    }
}