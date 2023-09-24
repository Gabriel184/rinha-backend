package br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.mapper.impl;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.mapper.PersonMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl implements PersonMapper {
    @Override
    public PersonDTO toDTO(Person person){
        return new PersonDTO(person);
    }

    @Override
    public Person toPerson(PersonDTO dto){
        return new Person(dto);
    }
}
