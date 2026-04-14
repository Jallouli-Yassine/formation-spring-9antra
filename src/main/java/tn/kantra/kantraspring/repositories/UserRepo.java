package tn.kantra.kantraspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.kantra.kantraspring.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

}
