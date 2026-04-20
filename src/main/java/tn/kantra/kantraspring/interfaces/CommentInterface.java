package tn.kantra.kantraspring.interfaces;

import tn.kantra.kantraspring.entities.Comment;

import java.util.List;

public interface CommentInterface {
    void ajouterComment(Long idUser, Long idPost, Comment c);
    List<Comment> findCommentsByPost(Long idPost);
}
