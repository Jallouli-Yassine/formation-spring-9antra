package tn.kantra.kantraspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.kantra.kantraspring.entities.Comment;
import tn.kantra.kantraspring.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("addComment/{idUser}/{idPost}")
    public void addComment(@PathVariable Long idUser,@PathVariable Long idPost ,@RequestBody Comment c){
        commentService.ajouterComment(idUser,idPost,c);
    }

    @GetMapping("getCommentsByPost")
    public List<Comment> getCommentsByPost(@RequestParam Long idPost){
        return commentService.findCommentsByPost(idPost);
    }

}
