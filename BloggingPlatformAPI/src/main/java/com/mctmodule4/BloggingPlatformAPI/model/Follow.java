package com.mctmodule4.BloggingPlatformAPI.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Follow.class, property = "followId")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;


    @ManyToOne
    @JoinColumn(name = "fk_actual_user")
    User currentUser;

    @ManyToOne
    @JoinColumn(name = "fk_follower_of_actual_user")
    User currentUserFollower;
}
