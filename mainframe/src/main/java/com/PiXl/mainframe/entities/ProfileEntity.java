package com.PiXl.mainframe.Entities;

import com.PiXl.mainframe.Models.Profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ProfileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(unique = true)
	private String username;
	
	private String firstName;
	
	private String secondName;
	
	@Column(length = 500)
	private String bio;
	
	private String foodPreference;
	
	private String profilePicUrl;

	public ProfileEntity(String username, String bio, String foodPreference, String profilePicUrl) {
		this.username = username;
		this.bio = bio;
		this.foodPreference = foodPreference;
		this.profilePicUrl = profilePicUrl;
	}
	
	
	/**
     * Convert from Profile DTO to ProfileEntity.
     *
     * @param profile Profile DTO
     */
	public ProfileEntity(Profile profile) {
		this.id = profile.getId();
		this.username = profile.getUsername();
		this.firstName = profile.getFirstName();
		this.secondName = profile.getSecondName();
		this.bio = profile.getBio();
		this.foodPreference = profile.getFoodPreference();
		this.profilePicUrl = profile.getProfilePicUrl();
	}
	

}
