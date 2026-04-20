package tn.kantra.kantraspring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private UserEntity user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


}
