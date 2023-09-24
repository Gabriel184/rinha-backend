package br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.mapper;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface PersonMapper {

    PersonDTO toDTO(Person person);

    Person toPerson(PersonDTO dto);

    default Set<PersonDTO> toDTOSet(Set<Person> peopleSet){
        if (peopleSet.isEmpty())
            return new HashSet<>();
        return peopleSet.stream().map(PersonDTO::new).collect(Collectors.toSet());
    }

}
