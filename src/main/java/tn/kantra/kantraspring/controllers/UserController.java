package tn.kantra.kantraspring.controllers;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.interfaces.UserInterface;
import tn.kantra.kantraspring.services.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    /*
    * post : ajout db
    * get : afficher db
    * put : modifier db
    * delete : supprimer db
    * patch : modifier partiellement db
    * */

    //UserService userService;
    private final UserInterface userInterface;
    public UserController (UserInterface ui){
        userInterface=ui;
    }

    @PostMapping("addUser")
    public UserEntity addUser(@RequestBody UserEntity u){
       return userInterface.addUser(u);
    }

    @PostMapping("addUserWCP")
    public UserEntity addUserWCP(@RequestBody UserEntity u){
        return userInterface.addUserWCP(u);
    }
    @PostMapping("addUserWTUN")
    public UserEntity addUserWTUN(@RequestBody UserEntity user){
        return userInterface.addUserWTUN(user);
    }
    @PostMapping("addListUsers")
    public List<UserEntity> addListUser(@RequestBody List<UserEntity> l){
        return userInterface.addListUser(l);
    }
    @GetMapping("getAllUser")
    public List<UserEntity> getAllUser(){
        return userInterface.getAllUser();
    }
    @DeleteMapping("deleteUser/{id}")
    public String deletUser(@PathVariable Long id){
        return userInterface.deletUser(id);
    }

    @PatchMapping("updateUser/{id}")
    public UserEntity updateUser(@RequestBody UserEntity newUser,@PathVariable Long id){
        return userInterface.updateUser(newUser,id);
    }

    @GetMapping("findUserByLastname")
    public List<UserEntity> findUserByLastname(@RequestParam String lastname){
        return userInterface.findUserByLastname(lastname);
    }
    @GetMapping("findUsersByDomain")
    public List<UserEntity> findUsersByDomain(@RequestParam String domain){
        return userInterface.findUsersByDomainEmail(domain);
    }

    @GetMapping("findUsersByNameSWX/{un}")
    public List<UserEntity> findUsersByNameSWX(@PathVariable String un){
        return userInterface.findUsersByUserNameSWX(un);
    }

}