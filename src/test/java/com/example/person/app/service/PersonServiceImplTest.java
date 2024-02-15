package com.example.person.app.service;

import com.example.person.app.exception.PersonServiceException;
import com.example.person.app.model.Person;
import com.example.person.app.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService = new PersonServiceImpl();

    List<Person> people = new ArrayList<>();

    @Before
    public void setup() {
        this.people.add(Person.builder().firstName("John").lastName("Doe").gender("Male").age(25).build());
        this.people.add(Person.builder().firstName("Alice").lastName("Johnson").gender("Female").age(35).build());
        this.people.add(Person.builder().firstName("Jane").lastName("Doe").gender("Male").age(36).build());
        this.people.add(Person.builder().firstName("Jane").lastName("Johnson").gender("Female").age(27).build());
    }

    @Test
    public void test_sort_people_success() {
        List<Person> sortPeople = this.personService.sortPeople(people);

        for (int i = 1; i < sortPeople.size(); i++) {
            Assertions.assertTrue(sortPeople.get(i - 1).getFirstName().compareTo(sortPeople.get(i).getFirstName()) <= 0);
            if (sortPeople.get(i - 1).getFirstName().equals(sortPeople.get(i).getFirstName())) {
                Assertions.assertTrue(sortPeople.get(i - 1).getLastName().compareTo(sortPeople.get(i).getLastName()) <= 0);
            }
        }
    }

    @Test
    public void test_sort_people_null_exception() {
        Assertions.assertThrows(PersonServiceException.class, () -> this.personService.sortPeople(null));
    }

    @Test
    public void test_group_by_gender_success() {
        Map<String, List<Person>> groupByGender = this.personService.groupByGender(people);

        for (Person person : people) {
            Assertions.assertTrue(groupByGender.get(person.getGender()).contains(person));
        }
    }

    @Test
    public void test_group_by_gender_null_exception() {
        Assertions.assertThrows(PersonServiceException.class, () -> this.personService.groupByGender(null));
    }

    @Test
    public void test_filter_age_success() {
        List<Person> filterPeople = this.personService.filterAge(people, 30);

        for (Person person : filterPeople) {
            Assertions.assertTrue(person.getAge() >= 30);
        }
    }

    @Test
    public void test_filter_age_null_people_exception() {
        Assertions.assertThrows(PersonServiceException.class, () -> this.personService.filterAge(null, 30));
    }

    @Test
    public void test_filter_age_null_age_exception() {
        Assertions.assertThrows(PersonServiceException.class, () -> this.personService.filterAge(null, null));
    }
}
