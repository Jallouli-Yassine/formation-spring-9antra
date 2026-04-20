package tn.kantra.kantraspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.kantra.kantraspring.entities.Comment;
import tn.kantra.kantraspring.entities.PostEntity;
import tn.kantra.kantraspring.entities.UserEntity;
import tn.kantra.kantraspring.interfaces.CommentInterface;
import tn.kantra.kantraspring.repositories.CommentRepo;
import tn.kantra.kantraspring.repositories.PostRepo;
import tn.kantra.kantraspring.repositories.UserRepo;

import java.util.List;

@Service
public class CommentService implements CommentInterface {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    CommentRepo commentRepo;

    @Override
    public void ajouterComment(Long idUser, Long idPost, Comment c) {
        UserEntity u = userRepo.findById(idUser).get();
        PostEntity p = postRepo.findById(idPost).get();
        c.setPost(p);
        c.setUser(u);
        commentRepo.save(c);
    }

    @Override
    public List<Comment> findCommentsByPost(Long idPost) {
        return  commentRepo.getCommentsByPostID(idPost);
    }

}
