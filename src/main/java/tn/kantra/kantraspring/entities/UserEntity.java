package tn.kantra.kantraspring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

}
