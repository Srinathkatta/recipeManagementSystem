package com.srinath.RecipeManagementSystem.controller;

import com.srinath.RecipeManagementSystem.model.Comment;
import com.srinath.RecipeManagementSystem.service.AuthenticationService;
import com.srinath.RecipeManagementSystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("comment/{recipeId}/{email}/{tokenVal}")
    public String addComment(@PathVariable Long recipeId, @RequestBody Comment comment, @PathVariable String email, @PathVariable String tokenVal){
        if(authenticationService.authenticate(email,tokenVal)){
            return commentService.addComment(comment,recipeId);
        }else{
            return "Not an authorised user to perform this activity!!!";
        }
    }

    @DeleteMapping("comment/{Id}/{email}/{tokenVal}")
    public String removeCommentById(@PathVariable Long Id,@PathVariable String email,@PathVariable String tokenVal){
        if(authenticationService.authenticate(email,tokenVal)){
            return commentService.removeComment(Id,email);
        }else{
            return "Not an authorised user to perform this activity!!!";
        }
    }

    @GetMapping("comment/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @GetMapping("comments/{recipeId}")
    public List<Comment> getAllCommentsOnRecipe(@PathVariable Long recipeId){
        return commentService.getAllCommentsOnRecipe(recipeId);
    }


}
