package br.com.muralis.gabriel.henrique.rinhabackend.inbound.controller;

import br.com.muralis.gabriel.henrique.rinhabackend.inbound.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "contagem-pessoas")
public class AuditCountPesonController {

    @Autowired
    private PersonFacade personFacade;

    @GetMapping
    public ResponseEntity<Number> countPeople() {
        return ResponseEntity
                .status(200)
                .body(this.personFacade.countPeople());
    }

}
