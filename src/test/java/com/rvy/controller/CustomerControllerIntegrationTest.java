package com.rvy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.CustomerSupermarketAppApplication;
import com.rvy.entity.Customer;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@SpringBootTest(classes = CustomerSupermarketAppApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	@LocalServerPort
	Integer port;
	
	
	@Test
	public void testCustomer()
	{
		Customer customer =  new Customer(null,17081,"LostMapanip","mapanip@gmail.com",9777172228L,LocalDate.of(2009, 1, 1),"Door 9","Street 7","Mumbai","Maharashtra","India",769007L,1214);;
		System.out.println(port);
		ResponseEntity<Customer> result = restTemplate.postForEntity("http://localhost:"+port.toString()+"/rvy/api/cm/v1/customers", customer, Customer.class);
		System.out.println(result.getBody().getCustomerId());
		assertEquals(result.getStatusCode(),HttpStatus.OK);
		restTemplate.delete("http://localhost:"+port.toString()+"/rvy/api/cm/v1/customers/"+result.getBody().getCustomerId());
	
	}
	@Test
	public void testCustomerDetail()
	{
		Customer customer =  new Customer(null,17082,"_inferno_","inf@gmail.com",9777172228L,LocalDate.of(2009, 1, 1),"Door 9","Street 7","Mumbai","Maharashtra","India",769007L,1214);
		ResponseEntity<Customer> result = restTemplate.postForEntity("http://localhost:"+port.toString()+"/rvy/api/cm/v1/customers", customer, Customer.class);
		customer.setCustomerId(result.getBody().getCustomerId());
		assertEquals(customer.getEmail(),result.getBody().getEmail());
		restTemplate.delete("http://localhost:"+port.toString()+"/rvy/api/cm/v1/customers/"+result.getBody().getCustomerId());
	}
	
	@Test
	public void testCulstomerList()
	{
		ResponseEntity<List> List =  restTemplate.getForEntity("http://localhost:"+port.toString()+"/rvy/api/cm/v1/customers", List.class);
		System.out.println(List);
		assertThat(List.getStatusCode().equals(HttpStatus.OK));
	
	}

}
