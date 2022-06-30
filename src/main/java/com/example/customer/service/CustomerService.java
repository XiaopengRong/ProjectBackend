package com.example.customer.service;
import java.util.List;

import com.example.customer.dto.CustomerDto;
import com.example.customer.entity.Customer;


public interface CustomerService {
	
	List<Customer> getAllCustomer(Integer pageNo, Integer pageSize, String sortBy);
	Customer customerRegister(CustomerDto customerDto);
	void deregisterCustomerById(Long id);
	

}
