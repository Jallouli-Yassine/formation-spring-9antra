package tn.kantra.kantraspring.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.kantra.kantraspring.entities.UserEntity;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    //name methode
    boolean existsByUsername(String username);
    List<UserEntity> findUserEntityByLastname(String lastname);
    UserEntity findUserEntityByUsername(String username);

    //jpql methode : nativeQuery = false par default
    @Query("select u from UserEntity u where u.username=?1")
    UserEntity findUserByUsernameJPQL(String username);

    //sql methode
    @Query(value = "SELECT * from users where username=:username",nativeQuery = true)
    UserEntity findUserByUsernameSQL(String username);

    @Query(value = "select * from users where substring_index(email,'@',-1) like CONCAT(:d,'%')" , nativeQuery = true)
    List<UserEntity> findUsersByDomail(String d);

    @Query(value = "select * from users where username like CONCAT(:x,'%')" , nativeQuery = true)
    List<UserEntity> findUsersByNameSWX(String x);



}