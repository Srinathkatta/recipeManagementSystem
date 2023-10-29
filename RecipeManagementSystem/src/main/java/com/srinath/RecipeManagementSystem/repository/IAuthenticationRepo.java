package com.srinath.RecipeManagementSystem.repository;




import com.srinath.RecipeManagementSystem.model.AuthenticationToken;
import com.srinath.RecipeManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByTokenValue(String authTokenValue);


    AuthenticationToken findFirstByUser(User user);

}

