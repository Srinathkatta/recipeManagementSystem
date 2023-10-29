package com.srinath.RecipeManagementSystem.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "User name should not be empty")
    private String userName;

    @NotBlank(message = "password should not be blank")
    private String userPassword;

    @Email(message = "email must be in acceptable format like something-@-something")
    @Column(unique = true)
    private String userEmail;

    @Enumerated(EnumType.STRING)
    private Gender gender;



}

