package com.java8.demo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.java8.demo.model.Customer;

public class StreamsTest {

	private List<Customer> customers; 
	
	@Before
	public void setup() {
		customers = ModelFactory.createCustomers();
	}
	
	@Test
	public void simpleStreamTest() {
		System.out.println(customers.size());
	}
}
