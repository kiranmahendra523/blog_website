package com.saikiran.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@Email(message="Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=5,message="Username must be minimum of 5 characters")
	private String name;
	
	@NotEmpty
	@Pattern(regexp ="..." ,message="At least 8 characters long\r\n"
			+ "Contains at least one uppercase letter\r\n"
			+ "Contains at least one lowercase letter\r\n"
			+ "Contains at least one digit\r\n"
			+ "Contains at least one special character (!@#$%^&*)")
	private String password;
	
	@NotEmpty
	@Size(min =50 , max=200 , message = "About must me min of 50 chars max of 200 chars")
	private String about;
}
