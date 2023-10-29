package com.srinath.RecipeManagementSystem.service;




import com.srinath.RecipeManagementSystem.model.Comment;
import com.srinath.RecipeManagementSystem.model.Recipe;
import com.srinath.RecipeManagementSystem.repository.ICommentRepo;
import com.srinath.RecipeManagementSystem.repository.IRecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IRecipeRepo recipeRepo;

    public String addComment(Comment comment, Long recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElse(null);
        if(recipe != null) {
            commentRepo.save(comment);
            return "comment added!!";
        }else{
            return "Invalid Id!!";
        }
    }

    public boolean authorisedCommentRemover(Comment comment,String email){
        String commentOwnerEmail = comment.getCommenter().getUserEmail();
        String recipeOwnerEmail = comment.getRecipe().getUser().getUserEmail();
        return commentOwnerEmail.equals(email) || recipeOwnerEmail.equals(email);
    }

    public String removeComment(Long id, String email) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment != null){
            if(authorisedCommentRemover(comment,email)){
                commentRepo.delete(comment);
                return "comment deleted successfully.";
            }else{
                return "comment can only be deleted by comment owner!!!";
            }
        }else{
            return "comment not exist!!";
        }
    }

    public void deleteAllCommentsOnRecipe(Recipe recipe){
        List<Comment> comments = commentRepo.findAllByRecipe(recipe);
        commentRepo.deleteAll(comments);
    }


    public Comment getCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElse(null);
        return comment;
    }

    public List<Comment> getAllCommentsOnRecipe(Long recipeId) {
        Recipe recipe = recipeRepo.findById(recipeId).orElse(null);
        return commentRepo.findAllByRecipe(recipe);

    }
}

