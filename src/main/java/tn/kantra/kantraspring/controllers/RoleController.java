package tn.kantra.kantraspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.RoleName;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.services.RoleService;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("addRole")
    public void addRole(@RequestBody RoleEntity r){
        roleService.addRole(r);
    }

    @PostMapping("assignRoleToUser/{roleName}/{userID}")
    public UserEntity assignRToU(@PathVariable RoleName roleName,@PathVariable Long userID){
       return roleService.assignRoleToUser(roleName,userID);
    }

}
