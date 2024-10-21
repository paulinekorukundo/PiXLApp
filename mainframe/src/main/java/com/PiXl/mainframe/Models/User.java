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
	private String profile_picture;
	private String bio;
	private Boolean logged_in_status;

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	public User(UserEntity user) {
		this(user.getUserId().getUser_id(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getProfile_picture(), user.getBio(), user.getLogged_in_status());
	}

	@Override
	public int compareTo(User user) {
		return Comparator.comparing(User::getEmail)
				.compare(this, user);
	}

}
