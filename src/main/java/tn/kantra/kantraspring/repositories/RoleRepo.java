package tn.kantra.kantraspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.RoleName;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long> {

    @Query("select r from RoleEntity r where r.name=?1")
    RoleEntity findRoleByRoleName(RoleName roleName);

}