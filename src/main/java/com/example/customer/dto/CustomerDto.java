package com.example.customer.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class CustomerDto {
	
	@Null
	private Long id;
	
	@NotNull
    @NotBlank(message = "username is mandatory")
    @Size(min = 5, max = 25, message = "State must consist of between 5 to 20 characters.")
    private String username;
	
    @NotBlank(message = "last name is mandatory")
    @Size(min = 5, max = 25, message = "State must consist of between 8 to 20 characters.")
    private String password;

    @Email
    @NotEmpty
    private String email;
    
    @NotBlank(message = "first name is mandatory")
    @Size(min = 2, max = 25, message = "State must consist of between 2 to 20 characters.")
    private String first_name;

    @NotBlank(message = "last name is mandatory")
    @Size(min = 2, max = 25, message = "State must consist of between 2 to 20 characters.")
    private String last_name;

    private int register;

    @NotNull
    private String address, city, state;
    
    @NotNull
    private Integer zip;
    
    private Long phone;
	
}
