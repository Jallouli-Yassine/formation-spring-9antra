package tn.kantra.kantraspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
//@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String name;

    @Column(name = "prenom")
    private String lastname;

    @Column(nullable = false , unique = true)
    private String email;

    private String password;

    private String cofirmPassword;

    private String username;

    //* to 1
    @ManyToOne
    @JsonIgnore
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts;
/*
    @ManyToMany(mappedBy = "users")
   private List<RoleEntity> roles;

 */

}
