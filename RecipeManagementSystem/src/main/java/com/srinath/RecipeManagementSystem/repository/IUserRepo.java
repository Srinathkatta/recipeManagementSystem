package com.srinath.RecipeManagementSystem.repository;


import com.srinath.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    public User findFirstByuserEmail(String userEmail);

}
