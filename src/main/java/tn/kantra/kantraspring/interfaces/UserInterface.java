package tn.kantra.kantraspring.interfaces;

import tn.kantra.kantraspring.entities.UserEntity;

import java.util.List;

public interface UserInterface {
    public UserEntity addUser(UserEntity u);
    UserEntity addUserWCP(UserEntity user);
    UserEntity addUserWTUN(UserEntity user);
    List<UserEntity> addListUser(List<UserEntity> listU);
    List<UserEntity> getAllUser();
    String deletUser(Long id);
    UserEntity updateUser(UserEntity u , Long id);
    List<UserEntity> findUserByLastname(String lastname);
    List<UserEntity> findUsersByDomainEmail(String domain);
    List<UserEntity> findUsersByUserNameSWX(String x);
}