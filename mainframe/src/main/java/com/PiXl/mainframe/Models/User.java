package com.PiXl.mainframe.Models;

import java.util.Objects;

import com.PiXl.mainframe.entities.UserEntity;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Comparable<User> {

	private String userId;
	private String username;
	private String email;
	private String password;
	private String profilePicture;
	private String bio;
	private Boolean loggedInStatus;

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	public User(UserEntity user) {
		this(user.getUserId().getUser_id(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getProfilePicture(), user.getBio(), user.getLoggedInStatus());
	}

	@Override
	public int compareTo(User user) {
		return Comparator.comparing(User::getEmail)
				.compare(this, user);
	}

}
