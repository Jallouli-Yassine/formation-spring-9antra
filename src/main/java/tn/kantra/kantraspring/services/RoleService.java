package tn.kantra.kantraspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.RoleName;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.interfaces.RoleInterface;
import tn.kantra.kantraspring.repositories.RoleRepo;
import tn.kantra.kantraspring.repositories.UserRepo;

@Service
public class RoleService implements RoleInterface {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public void addRole(RoleEntity r) {
        roleRepo.save(r);
    }

    @Override
    public UserEntity assignRoleToUser(RoleName roleName, Long userID) {
        RoleEntity r = roleRepo.findRoleByRoleName(roleName);
        UserEntity u = userRepo.findById(userID).get();
        u.setRole(r);
        return userRepo.save(u);
    }
}
