package com.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;

	@NotEmpty
	private String name;

	@Email(message = "Given email is not valid.")
	private String email;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$", message = "Password must be at least 8 characters long and contain at least one letter, one number, and one special character")
	@JsonIgnore
	private String password;

	@NotEmpty
	private String gender;

	@NotEmpty
	private String about;

	private String profile_img = "default.png";

	private Date registerDate = new Date();

	Set<RoleDto> roles = new HashSet<>();

	public UserDto(Integer id, String name, String email, String password, String gender, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.about = about;
	}

}
