package com.example.person.app.service;

import com.example.person.app.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    List<Person> sortPeople(List<Person> people);
    Map<String, List<Person>> groupByGender(List<Person> people);
    List<Person> filterAge(List<Person> people, Integer age);
}
