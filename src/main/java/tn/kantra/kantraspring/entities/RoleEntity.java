package tn.kantra.kantraspring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    // role nafsha eli fl table lokhra
    //mapped by tetkteb fl fils (eli andha cardinalite sghira)
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

/*
    @ManyToMany
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "roleID"),
            inverseJoinColumns = @JoinColumn(name="idUser")
    )
    private List<UserEntity> users;
*/
}