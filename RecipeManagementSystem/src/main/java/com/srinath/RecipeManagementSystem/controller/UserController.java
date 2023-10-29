package com.srinath.RecipeManagementSystem.controller;

import com.srinath.RecipeManagementSystem.model.Recipe;
import com.srinath.RecipeManagementSystem.model.User;
import com.srinath.RecipeManagementSystem.model.dto.SignInInput;
import com.srinath.RecipeManagementSystem.model.dto.SignUpOutput;
import com.srinath.RecipeManagementSystem.service.AuthenticationService;
import com.srinath.RecipeManagementSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("user/signUp")
    public SignUpOutput signUp(@RequestBody User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut/{email}/{token}")
    public String signOutUser(@PathVariable String email , @PathVariable String token){
        if(authenticationService.authenticate(email,token)) {
            return userService.signOutUser(email);
        }
        return "Sign out not allowed for non authenticated user";
    }

    @GetMapping("recipies")
    public List<Recipe> getAllRecipies(){

        return userService.getAllRecipies();
    }

    @PostMapping("recipe/{email}/{tokenVal}")
    public String createRecipe(@RequestBody Recipe recipe,@PathVariable String email,@PathVariable String tokenVal){
        if(authenticationService.authenticate(email,tokenVal)){
            return userService.postRecipe(recipe);
        }else{
            return "Not an authorised user for this activity";
        }
    }

    @PutMapping("recipe/{email}/{tokenVal}/{Id}")
    public String updateRecipeById(@PathVariable Long Id,@PathVariable String email,@PathVariable String tokenVal,@RequestBody String ingrediants){
        if(authenticationService.authenticate(email,tokenVal)){
            return userService.updateRecipeById(Id,email,ingrediants);
        }else{
            return "Not an authorised user to perform this activity";
        }
    }

    @DeleteMapping("recipe/{Id}/{email}/{tokenVal}")
    public String removeRecipeById(@PathVariable Long Id,@PathVariable String email,@PathVariable String tokenVal){
        if(authenticationService.authenticate(email,tokenVal)){
            return userService.removeRecipe(Id,email);
        }else{
            return "Not an authorised user to perform this activity!!!";
        }
    }

    @GetMapping("recipe/{Id}")
    public Recipe getRecipeById(@PathVariable Long Id){
        return userService.getRecipeById(Id);
    }

    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }



}
