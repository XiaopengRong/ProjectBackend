package com.example.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.DependentDto;
import com.example.customer.entity.Customer;
import com.example.customer.entity.Dependent;
import com.example.customer.exception.DuplicateEmailException;
import com.example.customer.exception.DuplicateuserNameException;
import com.example.customer.exception.NoSuchAccountException;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.DependentRepository;


@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final DependentRepository dependentRepository;
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	

	@Override
	public List<Customer> getAllCustomer(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Customer> pagedResult = customerRepository.findAll(paging);
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			
			return new ArrayList<>();
		}
	}
	
	@Transactional
	@Override
	public Customer customerRegister(CustomerDto customerDto) {
		  validateDto(customerDto);
		 
		  if(customerRepository.findByEmail(customerDto.getEmail())!= null) {
			  throw new DuplicateEmailException(customerDto.getEmail());
		  } else if (customerRepository.findByUsername(customerDto.getUsername())!= null) {
			  throw new DuplicateuserNameException(customerDto.getUsername());
		  }
		  
		              Customer customer1 = Customer.builder()
					  .first_name(customerDto.getFirst_name())
					  .zip(customerDto.getZip())
					  .phone(customerDto.getPhone())
					  .state(customerDto.getState())
					  .username(customerDto.getUsername())
					  .register(customerDto.getRegister())
					  .last_name(customerDto.getLast_name())
					  .city(customerDto.getCity())
					  .address(customerDto.getAddress())
					  .email(customerDto.getEmail())
					  .password(customerDto.getPassword())
					  .build();
					 
		      customerRepository.save(customer1);	  
		  
		  return customer1;
		}

	@Override
	public void deregisterCustomerById(Long id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer == null) {
			throw new NoSuchAccountException(id);
			
		}
		customer.setRegister(0);
		customerRepository.save(customer);
		
	}
	
	  private void validateDto(CustomerDto customerDto) {
	        var violations = validator.validate(customerDto);
	        if (!violations.isEmpty()) {
	            throw new IllegalArgumentException("Invalid DTO " + customerDto);
	        }
	    }

	@Override
	public Dependent addDependent(DependentDto dependentDto) {
		if(customerRepository.findById(dependentDto.getParentId())==null) {
			throw new NoSuchAccountException(dependentDto.getParentId());
		}
		
		Customer customer = customerRepository.findById(dependentDto.getParentId()).orElse(null);
		Dependent dependent= Dependent.builder().age(dependentDto.getAge())
				.first_name(dependentDto.getFirst_name()).last_name(dependentDto.getLast_name())
				.customer(customer)
				.parentId(dependentDto.getParentId()).build();
		return dependentRepository.save(dependent);
		
		
	}

}
