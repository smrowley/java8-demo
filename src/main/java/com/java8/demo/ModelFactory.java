package com.java8.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.java8.demo.model.Category;
import com.java8.demo.model.Customer;
import com.java8.demo.model.State;
import com.java8.demo.model.Transaction;

public class ModelFactory {
	
	private static final List<String> CUSTOMER_FIRST_NAMES;
	private static final List<String> CUSTOMER_LAST_NAMES;
	
	static {
		CUSTOMER_FIRST_NAMES = new ArrayList<>(Arrays.asList("Jim", "Elliott",
				"David", "Alec", "Chester", "Bill", "John", "Shane", "Matt", "Josh", "Keith",
				"Jessica", "Jamie", "Marshall", "Mike", "Sarah", "Beth", "Chelsea", "Doak", "Christina", "Molly", "Eddie", ""
				, "Miguel", "Irshad", "Lisa", "Ray", "Vinay", "Perry", "Greg", "Suzanne", "Randy", "Alec", "Dale"));
		CUSTOMER_LAST_NAMES = new ArrayList<>(Arrays.asList("Smith", "Johnson",
				"Wheatley", "Rowley", "Kyrus", "Sperlazza", "Fisher", "Robertson", "Richardson", "Taylor",
				"Canter", "Herbert", "Suarez", "Laczynski", "Miller", "Bogart", "Khan", "Gines", "Krishnan", "Minerovic", "Haibach"));
	}
	
	public static List<Customer> createCustomers() {
		List<Customer> customers = new ArrayList<>();
		int customerNum = (int) (Math.random() * 100 + 5000);
		
		for (int x = 0; x < customerNum; x++) {
			customers.add(createCustomer());
		}
		
		return customers;
	}
	
	private static Customer createCustomer() {
		Customer customer = new Customer();
		String name = CUSTOMER_FIRST_NAMES.get((int)(Math.random() * CUSTOMER_FIRST_NAMES.size()));
		name += " " + CUSTOMER_LAST_NAMES.get((int)(Math.random() * CUSTOMER_LAST_NAMES.size()));
		customer.setName(name);
		customer.setLocation(State.getRandom());
		customer.setBirthday(getRandomDate(LocalDate.of(1970, 1, 1), LocalDate.of(1995, 12, 31)));
		customer.setTransactions(createTransactions(customer));
		
		return customer;
	}
	
	private static LocalDate getRandomDate(LocalDate min, LocalDate max) {
	    long minDay = min.toEpochDay();
	    long maxDay = max.toEpochDay();
	    long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
	    return LocalDate.ofEpochDay(randomDay);
	}
	
	private static List<Transaction> createTransactions(Customer customer) {
		List<Transaction> transactions = new ArrayList<>();
		
		int transactionNum = (int) (Math.random() * 80 + 20);
		
		for (int x = 0; x < transactionNum; x++) {
			transactions.add(createTransaction(customer));
		}
		
		return transactions;
	}
	
	private static Transaction createTransaction(Customer customer) {
		Transaction transaction = new Transaction();
		
		transaction.setCategory(Category.getRandom());
		transaction.setCustomer(customer);
		transaction.setDate(getRandomDate(LocalDate.of(2013, 1, 1), LocalDate.of(2017, 1, 31)));
		transaction.setAmount(getAmount());
		
		return transaction;
	}
	
	private static BigDecimal getAmount() {
		BigDecimal min = new BigDecimal("1.50");
		BigDecimal max = new BigDecimal("900.00");
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
	    return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
}
