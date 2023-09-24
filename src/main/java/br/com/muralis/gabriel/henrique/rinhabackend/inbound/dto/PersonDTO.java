package br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PersonDTO implements Serializable {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String apelido;
    @Getter
    @Setter
    private String nome;
    @Getter
    @Setter
    private String nascimento;
    @Getter
    @Setter
    private List<String> stack;

    public PersonDTO (){}

    @JsonCreator
    public PersonDTO(String id,
                     @JsonProperty(value = "apelido", required = true) String apelido,
                     @JsonProperty(value = "nome", required = true) String nome,
                     @JsonProperty(value = "nascimento", required = true) String nascimento,
                     @JsonProperty(value = "stack", required = true) List<String> stack) {
        this.id = id;
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    public PersonDTO(Person p){
        this.id = p.getId();
        this. apelido = p.getApelido();
        this.nome = p.getNome();
        this.nascimento = p.getNascimento();
        if(p.getStack() == null)
            this.stack = null;
        else if(p.getStack().isEmpty())
            this.setStack(new ArrayList<>());
        else
            this.setStack(new ArrayList<>(p.getStack()));
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", apelido='" + apelido + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", stack=" + stack +
                '}';
    }
}