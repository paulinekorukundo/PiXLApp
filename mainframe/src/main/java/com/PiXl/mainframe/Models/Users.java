package com.PiXl.mainframe.Models;

import java.util.List;

public class Users {
	private String userId;
	private String username;
	private String email;
	private String password;
	private String profilePicture;
	private String bio;
	private Boolean loggedInStatus;

	public Users(String userId, String username, String email, String password, String profilePicture, String bio,
			List<String> foodPreferences) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.bio = bio;
		this.loggedInStatus = false;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Login method
	 * 
	 * @param email
	 * @param password
	 * @return boolean
	 * 
	 */
	public boolean login(String email, String password) {
		this.loggedInStatus = this.email.equals(email) && this.password.equals(password);
		return this.loggedInStatus;
	}

	/**
	 * Register method
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @return User object
	 */
	public static Users register(String username, String email, String password) {
		return new Users("1", username, email, password, "", "", null);
	}

}
