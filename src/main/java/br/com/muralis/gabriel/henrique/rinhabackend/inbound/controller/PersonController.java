package br.com.muralis.gabriel.henrique.rinhabackend.inbound.controller;

import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.PersonFacade;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "pessoas")
@Slf4j
public class PersonController {

    @Autowired
    private PersonFacade personFacade;

    @GetMapping(path = "{id}")
    public ResponseEntity<PersonDTO> findPessoasById(@PathVariable String id) {
        return ResponseEntity.status(200).body(this.personFacade.findPersonById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addNewPerson(@RequestBody @Valid PersonDTO personDTO) {
        log.info("Attempting to create.");
        PersonDTO dto = this.personFacade.addNewPerson(personDTO);
            return ResponseEntity
                    .status(201)
                    .header("Location", "/pessoas/" + dto.getId())
                    .body(dto);
    }

    @GetMapping
    public ResponseEntity<?> findByTerm(@RequestParam(value = "t") String t){
        try{
            if (t == null || t.isEmpty())
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok().body(this.personFacade.findByTerm(t));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
