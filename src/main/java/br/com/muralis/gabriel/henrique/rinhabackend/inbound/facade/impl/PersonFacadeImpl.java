package br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.impl;

import br.com.muralis.gabriel.henrique.rinhabackend.core.service.PersonService;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.PersonFacade;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonService personService;

    @Override
    public PersonDTO addNewPerson(PersonDTO dto) {
        var p = this.personMapper.toPerson(dto);
        this.personService.addNewPerson(p);
        return this.personMapper.toDTO(p);
    }

    @Override
    public PersonDTO findPersonById(String id) {
        return this.personMapper.toDTO(this.personService.findPersonById(id));
    }

    @Override
    public Number countPeople() {
        return this.personService.countPeople();
    }

    @Override
    public Set<PersonDTO> findByTerm(String t) {
        return this.personMapper.toDTOSet(this.personService.findByTerm(t));
    }

}
