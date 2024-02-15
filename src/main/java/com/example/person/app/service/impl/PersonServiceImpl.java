package com.example.person.app.service.impl;

import com.example.person.app.exception.PersonErrorCode;
import com.example.person.app.exception.PersonServiceException;
import com.example.person.app.model.Person;
import com.example.person.app.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> sortPeople(List<Person> people) {
        try {
            log.info("Test No.16");
            if (CollectionUtils.isEmpty(people)) {
                throw new PersonServiceException(
                        HttpStatus.BAD_REQUEST,
                        PersonErrorCode.BAD_REQUEST,
                        "Argument people not found. ");
            }

            // Sort list of people with firstname and lastname
            people.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName));

            // Print log
            for (Person person : people) {
                log.info(String.valueOf(person));
            }

            return people;
        } catch (Exception ex) {
            log.info("[Error] sort people exception message: {}", ex.getMessage());
            throw ex;
        }

    }

    @Override
    public Map<String, List<Person>> groupByGender(List<Person> people) {
        try {
            log.info("Test No.17");
            if (CollectionUtils.isEmpty(people)) {
                throw new PersonServiceException(
                        HttpStatus.BAD_REQUEST,
                        PersonErrorCode.BAD_REQUEST,
                        "Argument people not found. ");
            }

            // Group list of people by gender condition
            Map<String, List<Person>> groupByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));

            // Print log
            log.info("Group by gender: {}", groupByGender);
            groupByGender.forEach((gender, persons) -> {
                log.info("Gender: {}", gender);
                persons.forEach(person -> log.info(String.valueOf(person)));
            });

            return groupByGender;
        } catch (Exception ex) {
            log.info("[Error] group by gender exception message: {}", ex.getMessage());
            throw ex;
        }

    }

    @Override
    public List<Person> filterAge(List<Person> people, Integer age) {
        try {
            log.info("Test No.18");
            if (CollectionUtils.isEmpty(people) || age == null) {
                throw new PersonServiceException(
                        HttpStatus.BAD_REQUEST,
                        PersonErrorCode.BAD_REQUEST,
                        "Argument people not found. ");
            }

            // Filter people that equal and older than 30 yrs
            List<Person> filterPeople = people.stream().filter(person -> person.getAge() >= age).collect(Collectors.toList());
            log.info(String.format("People older than [%s]: [%s]", age, filterPeople));

            // Print log
            log.info(String.format("Persons older than or equal to [%s]", age));
            filterPeople.forEach(person -> log.info(String.valueOf(person)));

            return filterPeople;
        } catch (Exception ex) {
            log.info("[Error] filter age exception message: {}", ex.getMessage());
            throw ex;
        }

    }

}
