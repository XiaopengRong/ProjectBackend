package com.example.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String username;

	    private String password;

	    private String email;

	    private String first_name;

	    private String last_name;

	    private int register;

	    private String address, city, state;
	    
	    private Integer zip;
	    
	    private Long phone;
        
	    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonManagedReference
	    private List<Dependent> dependent;
	    

	
	

}
