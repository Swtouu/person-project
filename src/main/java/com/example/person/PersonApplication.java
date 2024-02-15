package com.example.person;


import com.example.person.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class PersonApplication {

	private static final String[] FIRST_NAMES = {"John", "Jane", "Fred", "Alice", "Bob", "Charlie", "David", "Eve"};
	private static final String[] LAST_NAMES = {"Smith", "Doe", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Moore"};
	private static final String[] GENDERS = {"Male", "Female"};

	public static void main(String[] args) {
		List<Person> people = new ArrayList<>();
		Random random = new Random();

		// Generate list of people
		for (int i = 0; i < 20; i++) {
			String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
			String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
			String gender = GENDERS[random.nextInt(GENDERS.length)];
			int age = random.nextInt(50) + 10;

			people.add(Person.builder().firstName(firstName).lastName(lastName).gender(gender).age(age).build());
		}

		// Test No.16
		sortPeople(people);

		// Test No.17
		groupByGender(people);

		// Test No.18
		filterAge(people);
	}

	private static void sortPeople(List<Person> people) {
		log.info("Test No.16");
		// Sort list of people with firstname and lastname
		people.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName));

		// Print log
		for (Person person : people) {
			log.info(String.valueOf(person));
		}
	}

	private static void groupByGender(List<Person> people) {
		log.info("Test No.17");
		// Group list of people by gender condition
		Map<String, List<Person>> groupByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));

		// Print log
		log.info("Group by gender: {}", groupByGender);
		groupByGender.forEach((gender, persons) -> {
			log.info("Gender: {}", gender);
			persons.forEach(person -> log.info(String.valueOf(person)));
		});
	}

	private static void filterAge(List<Person> people) {
		log.info("Test No.18");
		// Filter people that equal and older than 30 yrs
		List<Person> filterPeople = people.stream().filter(person -> person.getAge() >= 30).collect(Collectors.toList());
		log.info("People older than 30: {}", filterPeople);

		// Print log
		log.info("Persons older than or equal to 30");
		filterPeople.forEach(person -> log.info(String.valueOf(person)));
	}


}
