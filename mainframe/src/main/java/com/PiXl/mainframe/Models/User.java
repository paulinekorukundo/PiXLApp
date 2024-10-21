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

	private String email;
	private String password;
	private Boolean logged_in_status;

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	public User(UserEntity user) {
		this(user.getEmail(), user.getPassword(), user.getLogged_in_status());
	}

	@Override
	public int compareTo(User user) {
		return Comparator.comparing(User::getEmail)
				.compare(this, user);
	}

}
