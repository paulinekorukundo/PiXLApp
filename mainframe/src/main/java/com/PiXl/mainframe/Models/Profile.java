package com.PiXl.mainframe.Models;


import com.PiXl.mainframe.Entities.ProfileEntity;

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

	private Long id;
	
	@Setter
	private String firstName;
	
	@Setter
	private String secondName;
	
	@Column(unique = true)
	private String username;
	
	@Setter
	@Column(length = 500)
	private String bio;
	
	@Setter
	private String foodPreference;
	
	@Setter
	private String profilePicUrl;
	

	/**
	 * Custom toString method
	 */
	@Override
	public String toString() {
		return "Username: " + username + ", fistname: " + firstName + ", secondname: " + secondName;
	}
	
	/**
	 * Constructor to convert from ProfileEntity to Profile Object
	 * @param pe Profile Entity
	 */
	public Profile(ProfileEntity pe) {
		this(pe.getId(),
				pe.getUsername(),
				pe.getBio(),
				pe.getFirstName(),
				pe.getSecondName(),
				pe.getFoodPreference(),
				pe.getProfilePicUrl()
				);
	}

}
