package tn.kantra.kantraspring.interfaces;

import tn.kantra.kantraspring.entities.RoleEntity;
import tn.kantra.kantraspring.entities.RoleName;
import tn.kantra.kantraspring.entities.UserEntity;

public interface RoleInterface {
    void addRole(RoleEntity r);
    public UserEntity assignRoleToUser(RoleName roleName, Long userID);
}
