package tn.kantra.kantraspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.interfaces.UserInterface;
import tn.kantra.kantraspring.repositories.UserRepo;

import java.util.List;

@Service
public class UserService implements UserInterface {

    //@Autowired
    UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public UserEntity addUser(UserEntity u){
        return userRepo.save(u);
    }

    @Override
    public UserEntity addUserWCP(UserEntity user) {
        if(!user.getPassword().equals(user.getCofirmPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "password and confirm password are not the same"
            );
        }else {
            return userRepo.save(user);
        }
    }

    @Override
    public UserEntity addUserWTUN(UserEntity user) {
        if(userRepo.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "username already exists"
            );
        }else return userRepo.save(user);
    }

    @Override
    public List<UserEntity> addListUser(List<UserEntity> listU) {
        return userRepo.saveAll(listU);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public String deletUser(Long id){
        UserEntity u = userRepo.findById((id)).get();
        if(u!=null)
        {
            userRepo.deleteById(id);
            return "user deleted";
        }
        else return "user not found";
    }

    @Override
    public UserEntity updateUser(UserEntity u , Long id){
        UserEntity oldUser = userRepo.findById(id).get();
        if (oldUser!=null){

            if(u.getName()!=null) oldUser.setName(u.getName());
            if(u.getLastname()!=null) oldUser.setLastname(u.getLastname());
            if(u.getEmail() != null) oldUser.setEmail(u.getEmail());
            if(u.getUsername() != null) oldUser.setUsername(u.getUsername());
            return userRepo.save(oldUser);
        }
        return null;

    }

    @Override
    public List<UserEntity> findUserByLastname(String lastname) {
        return userRepo.findUserEntityByLastname(lastname);
    }

    @Override
    public List<UserEntity> findUsersByDomainEmail(String domain) {
        return userRepo.findUsersByDomail(domain);
    }

    @Override
    public List<UserEntity> findUsersByUserNameSWX(String x) {
       return userRepo.findUsersByNameSWX(x);
    }

}
