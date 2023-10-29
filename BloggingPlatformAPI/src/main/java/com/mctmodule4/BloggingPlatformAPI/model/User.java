package com.mctmodule4.BloggingPlatformAPI.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mctmodule4.BloggingPlatformAPI.model.enums.Gender;
import com.mctmodule4.BloggingPlatformAPI.model.enums.PostType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = User.class, property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;

    @Column(unique = true)
    private String userHandle;
    private String userBio;

    @Pattern(regexp = "^.+@.+\\.(com)$", message = "Please enter valid email")
    private String userEmail;


    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String userPassword;

    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String confirmPassword;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private PostType postType;



    @OneToMany(mappedBy = "postOwner")
    private List<Post> postList;

    @OneToMany(mappedBy = "currentUserFollower")
    private List<Follow> followers;


}
