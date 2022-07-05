package com.example.customer.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.customer.dto.CustomerDto;
import com.example.customer.dto.DependentDto;
import com.example.customer.entity.Customer;
import com.example.customer.entity.Dependent;
import com.example.customer.service.CustomerService;

import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_V_0_1_CUSTOMERS)
public class CustomerController {
	public static final String MAPPING = EndpointConstants.API_V_0_1_CUSTOMERS;
	private final CustomerService customerService;
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "50") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
        List<Customer> customers = customerService.getAllCustomer(pageNo, pageSize, sortBy);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }
    
    @PostMapping(value = "/register",consumes = {MediaType.APPLICATION_JSON_VALUE}
    ,produces = {MediaType.APPLICATION_JSON_VALUE,})
    public ResponseEntity<Customer> createNewAccount(@Valid @RequestBody CustomerDto customerDto){
        System.out.println("############################################");
        System.out.print("connected ");
        var customer = customerService.customerRegister(customerDto);

        var uri = URI.create(MAPPING+"/"+customer.getId());
        return ResponseEntity.created(uri).body(customer);
    }
    
	@PutMapping(value = "/{customerId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> deregisterCustomer(@PathVariable("customerId") Long customerId){
		customerService.deregisterCustomerById(customerId);
		return ResponseEntity.noContent().build();
	}
    
    @PostMapping(value = "/addDependent",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    ,produces = {MediaType.APPLICATION_JSON_VALUE,})
    public ResponseEntity<Dependent> addDependent(@Valid @RequestBody DependentDto dependentDto){

        var dependent = customerService.addDependent(dependentDto);

        var uri = URI.create(MAPPING+"/"+dependent.getId());
        return ResponseEntity.created(uri).body(dependent);
    }
 
	
}
