package com.srinath.RecipeManagementSystem.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id", nullable = false)
    private Recipe recipe;

    @NotBlank(message = "content should not be empty")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User commenter;

}


