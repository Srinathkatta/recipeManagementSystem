package com.srinath.RecipeManagementSystem.service;




import com.srinath.RecipeManagementSystem.model.AuthenticationToken;
import com.srinath.RecipeManagementSystem.model.Recipe;
import com.srinath.RecipeManagementSystem.model.User;
import com.srinath.RecipeManagementSystem.model.dto.SignInInput;
import com.srinath.RecipeManagementSystem.model.dto.SignUpOutput;
import com.srinath.RecipeManagementSystem.repository.IRecipeRepo;
import com.srinath.RecipeManagementSystem.repository.IUserRepo;
import com.srinath.RecipeManagementSystem.service.emailUtility.EmailHandler;
import com.srinath.RecipeManagementSystem.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    CommentService commentService;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();
        if (newEmail == null) {
            signUpStatus = false;
            signUpStatusMessage = "invalid email";
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        //check if this user email already exists ??

        User existingUser = userRepo.findFirstByuserEmail(newEmail);
        if (existingUser != null) {
            signUpStatus = false;
            signUpStatusMessage = "user already registered";
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //save the user with the new encrypted password
            user.setUserPassword(encryptedPassword);

            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "user successfully registered");

        } catch (Exception e) {
            signUpStatus = false;
            signUpStatusMessage = "internal error occured during sign up";
            return new SignUpOutput(signUpStatus, signUpStatusMessage);
        }

    }

    public String signInUser(SignInInput signInInput){

        String signInStatusMessage = null;
        String signInEmail = signInInput.getEmail();

        if(signInEmail==null){
            signInStatusMessage = "invalid email";
            return signInStatusMessage;
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByuserEmail(signInEmail);

        if(existingUser==null){
            signInStatusMessage = "user not registered";
            return signInStatusMessage;
        }


        //match passwords :

        //hash the password: encrypt the password

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());

            if(encryptedPassword.equals(existingUser.getUserPassword())){

                AuthenticationToken authToken = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail(signInEmail,"sent token for authorisation",authToken.getTokenValue());
                return "Token sent to your email";

            }
            else{
                signInStatusMessage = "invalid credentials";
                return signInStatusMessage;
            }

        } catch (Exception e) {
            signInStatusMessage = "internal error occured during sign in";
            return signInStatusMessage;
        }

    }

    public String signOutUser(String email){
        User user = userRepo.findFirstByuserEmail(email);
        AuthenticationToken authToken = authenticationService.findFirstByUser(user);
        authenticationService.removeAuthToken(authToken);
        return "session ended";
    }

    public List<Recipe> getAllRecipies() {
        return recipeRepo.findAll();
    }

    public String postRecipe(Recipe recipe) {
        recipeRepo.save(recipe);
        return "recipe added";
    }

    public String updateRecipeById(Long id, String email, String ingrediants) {
        Recipe recipe = recipeRepo.findById(id).orElse(null);
        if(recipe!=null){
            String recipeOwnerEmail = recipe.getUser().getUserEmail();
            if(recipeOwnerEmail.equals(email)){
                recipe.setIngredients(ingrediants);
                return "recipe updated.";
            }else{
                return "Only recipe Owner can able to update the recipe!!!";
            }
        }else{
            return "recipe not exist!!";
        }
    }


    public String removeRecipe(Long id, String email) {
        Recipe recipe = recipeRepo.findById(id).orElse(null);
        if(recipe!=null){
            String recipeOwnerEmail = recipe.getUser().getUserEmail();
            if(recipeOwnerEmail.equals(email)){
                commentService.deleteAllCommentsOnRecipe(recipe);
                recipeRepo.delete(recipe);
                return "Recipe removed.";
            }else{
                return "Only recipe Owner can able to remove the recipe!!!";
            }
        }else{
            return "Recipe not exist!!";
        }
    }


    public Recipe getRecipeById(Long id) {
        Recipe recipe = recipeRepo.findById(id).orElse(null);
        return recipe;
    }

    public List<Recipe> getAllRecipiesOfType(String recipeType) {
        return recipeRepo.findAllByRecipeType(recipeType);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }
}

