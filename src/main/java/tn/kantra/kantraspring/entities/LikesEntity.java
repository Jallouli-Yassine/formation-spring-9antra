package tn.kantra.kantraspring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer likeCount;

    @ManyToOne
    private Comment comment;

    @OneToOne
    private UserEntity user;

}
