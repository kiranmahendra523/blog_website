package com.saikiran.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroundDto {

	private int groundId;
	
	@NotEmpty
	@Size(min=5,max=20,message="Ground should be minimum of 3 characters or maximum of 20 characters")
	private String groundName;
	@NotEmpty
	@Size(min=20,max=200,message="Description should be minimum of 20 characters or maximum of 200 characters")
	private String groundDescrption;

	private int groundCostPerHour;
	
	private boolean avalibiltyType;

}