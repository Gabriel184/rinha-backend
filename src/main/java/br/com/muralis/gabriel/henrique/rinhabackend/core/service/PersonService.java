package br.com.muralis.gabriel.henrique.rinhabackend.core.service;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;

import java.util.Set;

public interface PersonService {

    void validateAddNewPerson(Person p);

    void validateUUIDFindPerson(String id);
    Person addNewPerson (Person p);

    Person findPersonById(String id);

    Number countPeople();

    Set<Person> findByTerm(String t);

}
