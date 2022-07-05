package com.example.customer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DependentDto {
	

	private Long parentId;
	
    @NotBlank(message = "first name is mandatory")
	@Size(min = 2, max = 25, message = "State must consist of between 2 to 20 characters.")
	private String first_name;
    
    
    @NotBlank(message = "first name is mandatory")
    @Size(min = 2, max = 25, message = "State must consist of between 2 to 20 characters.")
	private String last_name;
	
	
	private int age;

}
