package tn.kantra.kantraspring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JsonIgnore
    private PostEntity post;

    @OneToMany(mappedBy = "comment")
    private List<LikesEntity> likes;

    @ManyToOne
    private UserEntity user;

}