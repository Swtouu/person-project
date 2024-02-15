package com.example.person.app;


import com.example.person.app.model.Person;
import com.example.person.app.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.*;


@Slf4j
@SpringBootApplication
public class PersonApplication implements CommandLineRunner {

	@Autowired
	PersonService personService;

	private static final String[] FIRST_NAMES = {"John", "Jane", "Fred", "Alice", "Bob", "Charlie", "David", "Eve"};
	private static final String[] LAST_NAMES = {"Smith", "Doe", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore"};
	private static final String[] GENDERS = {"Male", "Female"};

	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args).close();
	}

	@Override
	public void run(String... args){
		List<Person> people = new ArrayList<>();
		SecureRandom random = new SecureRandom();

		// Generate list of people
		for (int i = 0; i < 20; i++) {
			String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
			String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
			String gender = GENDERS[random.nextInt(GENDERS.length)];
			int age = random.nextInt(50) + 10;

			people.add(Person.builder().firstName(firstName).lastName(lastName).gender(gender).age(age).build());
		}

		// Test No.16
		this.personService.sortPeople(people);

		// Test No.17
		this.personService.groupByGender(people);

		// Test No.18
		this.personService.filterAge(people, 30);
	}

}
