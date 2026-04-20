package tn.kantra.kantraspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.kantra.kantraspring.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.post.id=?1")
    List<Comment> getCommentsByPostID(Long idPost);
}