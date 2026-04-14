package tn.kantra.kantraspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.interfaces.UserInterface;
import tn.kantra.kantraspring.repositories.UserRepo;

@Service
public class UserService implements UserInterface {

    @Autowired
    UserRepo userRepo;
  /*
    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }
   */

    @Override
    public UserEntity addUser(UserEntity u) {
        return userRepo.save(u);
    }
}
