package br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade;

import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;

import java.util.Set;

public interface PersonFacade {

    PersonDTO addNewPerson (PersonDTO dto);

    PersonDTO findPersonById(String id);

    Number countPeople();

    Set<PersonDTO> findByTerm(String t);

}
