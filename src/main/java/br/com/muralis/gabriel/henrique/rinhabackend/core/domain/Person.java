package br.com.muralis.gabriel.henrique.rinhabackend.core.domain;

import br.com.muralis.gabriel.henrique.rinhabackend.inbound.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "PESSOAS")
public class Person {
    @Id
    @Getter
    @Setter
    @Column(columnDefinition = "uuid")
    private String id;
    @Getter
    @Setter
    @Column(length = 32,  nullable = false, unique = true)
    private String apelido;
    @Getter
    @Setter
    @Column(length = 100, nullable = false, unique = true)
    private String nome;
    @Getter
    @Setter
    @Column(length = 10, nullable = false)
    private String nascimento;
    @Column(length = 2000)
    private String stack;
    @Getter
    @Setter
    @Column(name = "termodebusca", length = 2000)
    private String termoDeBusca;
    public Person(){}

    public Person(PersonDTO p){
        this.id = p.getId();
        this. apelido = p.getApelido();
        this.nome = p.getNome();
        this.nascimento = p.getNascimento();
        if(p.getStack() == null)
            this.stack = null;
        else if(p.getStack().isEmpty())
            this.setStack(new HashSet<>());
        else
            this.setStack(new HashSet<>(p.getStack()));
        this.termoDeBusca = this.nome + this.apelido + this.stack;
    }

    public Set<String> getStack() {
        return this.stack == null ? null : Set.of(stack.split(","));
    }

    public void setStack(Set<String> stack) {
        if (stack == null)
            this.stack = null;
        else
            this.stack = String.join(",", stack);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(apelido, person.apelido) && Objects.equals(nome, person.nome) && Objects.equals(nascimento, person.nascimento) && Objects.equals(stack, person.stack) && Objects.equals(termoDeBusca, person.termoDeBusca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apelido, nome, nascimento, stack, termoDeBusca);
    }
}
