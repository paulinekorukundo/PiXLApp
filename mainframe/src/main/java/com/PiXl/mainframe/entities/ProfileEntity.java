package com.PiXl.mainframe.entities;

import com.PiXl.mainframe.models.Profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Profile")
public class ProfileEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    
    @Column(name = "user_id", nullable = false)
    private String userId;
    
    private String firstName;
    private String lastName;
    
    @Column(length = 500)
    private String bio;
    
    private String foodPreferences;
    
    private String profilePicture;

	public ProfileEntity(String firstName, String lastName, String bio, String foodPreferences,
			String profilePicture) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.foodPreferences = foodPreferences;
		this.profilePicture = profilePicture;
	}
	
	
	/**
     * Convert from Profile DTO to ProfileEntity.
     *
     * @param profile Profile DTO
     */
	public ProfileEntity(Profile profile) {
		this.profileId = profile.getProfileId();
		this.userId = profile.getUserId();
		this.firstName = profile.getFirstName();
		this.lastName = profile.getLastName();
		this.bio = profile.getBio();
		this.foodPreferences = profile.getFoodPreference();
		this.profilePicture = profile.getProfilePicture();
	}

}
