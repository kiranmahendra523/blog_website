package com.saikiran.blog.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	@NotEmpty
	@Size(min=5,max = 25,message="title must be minimum of 5 characters and max of 25 ")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=20,max=100,message="description must be minimum of 20 characters and max of 100")
	private String categoryDescription;
}
