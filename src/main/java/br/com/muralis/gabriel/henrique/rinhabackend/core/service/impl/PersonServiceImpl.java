package br.com.muralis.gabriel.henrique.rinhabackend.core.service.impl;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;
import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.DomainException400;
import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.DomainException404;
import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.DomainException422;
import br.com.muralis.gabriel.henrique.rinhabackend.core.service.PersonService;
import br.com.muralis.gabriel.henrique.rinhabackend.outbound.postgres.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    private final Pattern UUIDRegex = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}");

    private final Pattern DatePattern = Pattern.compile("yyyy-MM-dd");

    public final Set<String> nicknames = new HashSet<>(1000);

    @Override
    public void validateAddNewPerson(Person p) {
        if (p.getNome() != null) {
            try {
                Integer.parseInt(p.getNome());
                throw DomainException400.my400ErrorDescription("Name cannot be a number.");
            } catch (NumberFormatException ignored) {

            }
            if (p.getNome().length() > 100)
                throw DomainException422.my422ErrorDescription("Name only can be under 100 chars.");
        } else {
            throw DomainException422.my422ErrorDescription("Name cannot be null.");
        }

        if (p.getApelido() != null) {
            try {
                Integer.parseInt(p.getApelido());
                throw DomainException400.my400ErrorDescription("NickName cannot be a number.");
            } catch (NumberFormatException ignored) {

            }
            if (p.getApelido().length() > 32)
                throw DomainException422.my422ErrorDescription("NickName only can be under 32 chars.");
            if (this.alreadyHasNicknamePersisted(p.getApelido()))
                throw DomainException422.my422ErrorDescription("NickName duplicated");
        } else {
            throw DomainException422.my422ErrorDescription("Nickname cannot be null.");
        }
        if (p.getNascimento() != null) {
            if (p.getNascimento().matches(DatePattern.pattern()))
                throw DomainException404.my404ErrorDescription("Invalid date, the date is not in a correct pattern yyyy-MM-dd");

            try {
                var date = LocalDate.parse(p.getNascimento());
            } catch (DateTimeException ex) {
                throw DomainException422.my422ErrorDescription("Invalid date.");
            }
        } else {
            throw DomainException422.my422ErrorDescription("BirthDate cannot be null.");
        }
        if (p.getStack() != null) {
            long haveNullElements = p.getStack().stream().filter(e -> e.equals("null")).count();
            if (haveNullElements != 0)
                throw DomainException422.my422ErrorDescription("Stack cannot contains null elements.");
            p.getStack().stream().forEach(e -> {
                try {
                    Integer.parseInt(e);
                    throw DomainException400.my400ErrorDescription("Stack cannot contains number elements.");
                } catch (NumberFormatException ignored) {

                }
            });
        }

    }

    @Override
    public void validateUUIDFindPerson(String id) {
        String idToValidate = Optional.ofNullable(id).orElseThrow(() -> DomainException404.my404ErrorDescription("Invalid id."));
        if (idToValidate.isEmpty())
            throw DomainException404.my404ErrorDescription("Invalid id, the id is empty.");
        if (idToValidate.length() < 36)
            throw DomainException404.my404ErrorDescription("Invalid id, the id does not is long enough.");
        if (!idToValidate.matches(UUIDRegex.pattern()))
            throw DomainException404.my404ErrorDescription("Invalid id, the id does not is a valid UUID");
    }

    @Override
    public Person addNewPerson(Person p) {
        this.validateAddNewPerson(p);
        p.setId(UUID.randomUUID().toString());
        p = personRepository.saveAndFlush(p);
        nicknames.add(p.getApelido());
        return p;
    }

    @Override
    public Person findPersonById(String id) {
        this.validateUUIDFindPerson(id);
        return this.personRepository.findById(id).orElseThrow(() -> DomainException404.my404ErrorDescription("Person not found."));
    }

    @Override
    public Number countPeople() {
        return this.personRepository.countPeople();
    }

    @Override
    public Set<Person> findByTerm(String t) {
        return this.personRepository.findByTerm(t);
    }

    private boolean alreadyHasNicknamePersisted(String nickname){
        if (this.nicknames.contains(nickname)) {
            needToClearNickNameSet();
            return true;
        }
        return this.personRepository.existsByApelido(nickname);
    }

    private void needToClearNickNameSet(){
        if (this.nicknames.size() > 3000)
            this.nicknames.clear();
    }
}
