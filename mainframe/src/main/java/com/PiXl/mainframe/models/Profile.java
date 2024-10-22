package com.PiXl.mainframe.models;


import com.PiXl.mainframe.entities.ProfileEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Profile {

	private Long profileId;
	
	private String userId;
	
	@Setter
	private String firstName;
	
	@Setter
	private String lastName;
	
	@Setter
	@Column(length = 500)
	private String bio;
	
	@Setter
	private String foodPreference;
	
	@Setter
	private String profilePicture;
	

	/**
	 * Custom toString method
	 */
	@Override
	public String toString() {
		return "fistname: " + firstName + ", lastname: " + lastName;
	}
	
	/**
	 * Constructor to convert from ProfileEntity to Profile Object
	 * @param pe Profile Entity
	 */
	public Profile(ProfileEntity pe) {
		this(
				pe.getProfileId(),
				pe.getUserId(),
				pe.getFirstName(),
				pe.getLastName(),
				pe.getBio(),
				pe.getFoodPreferences(),
				pe.getProfilePicture()
				);
	}

}
