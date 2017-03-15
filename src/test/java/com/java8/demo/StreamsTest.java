package com.java8.demo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.java8.demo.model.Category;
import com.java8.demo.model.Customer;
import com.java8.demo.model.State;
import com.java8.demo.model.Transaction;

public class StreamsTest {

	private static List<Customer> customers;
	
	private static Logger LOG = Logger.getLogger(StreamsTest.class.getSimpleName());
	
	@BeforeClass
	public static void setup() {
		LOG.info("setup");
		customers = ModelFactory.createCustomers();
		LOG.info("Customer size: " + customers.size());
	}
	
	@Test
	public void simpleStreamTest() {
		Instant start = Instant.now();
		
		BigDecimal totalAmount = customers.stream()
		.filter(customer -> State.VA.equals(customer.getLocation()))
		//.peek(System.out::println)
		.map(Customer::getTransactions)
		.filter(Objects::nonNull)
		.flatMap(transactions -> transactions.stream())
		.filter(transaction -> Category.APPAREL.equals(transaction.getCategory()))
		.map(Transaction::getAmount)
		.reduce(BigDecimal.ZERO, (total, newAmount) -> total.add(newAmount)).setScale(2);
		
		Instant finish = Instant.now();
		
		LOG.info("Single stream: " + Duration.between(start, finish));
		
		LOG.info("Total amount: " + totalAmount);
	}
		
	@Test
	public void parallelStreamTest() {
		Instant start = Instant.now();
		
		BigDecimal totalAmount = customers.parallelStream()
		.filter(customer -> State.VA.equals(customer.getLocation()))
		//.peek(System.out::println)
		.map(Customer::getTransactions)
		.filter(Objects::nonNull)
		.flatMap(transactions -> transactions.stream())
		.filter(transaction -> Category.APPAREL.equals(transaction.getCategory()))
		.map(Transaction::getAmount)
		.reduce(BigDecimal.ZERO, (total, newAmount) -> total.add(newAmount)).setScale(2);
		
		Instant finish = Instant.now();
		
		LOG.info("Parallel stream: " + Duration.between(start, finish));
		
		LOG.info("Total amount: " + totalAmount);
	}
	
}
