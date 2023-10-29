package com.srinath.RecipeManagementSystem.repository;




import com.srinath.RecipeManagementSystem.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepo extends JpaRepository<Recipe,Long> {
    List<Recipe> findAllByRecipeType(String recipeType);

}

