package br.com.muralis.gabriel.henrique.rinhabackend.outbound.postgres;

import br.com.muralis.gabriel.henrique.rinhabackend.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Query(value = "SELECT COUNT(*) FROM PESSOAS", nativeQuery = true)
    Number countPeople();

    @Query(value = "SELECT * FROM PESSOAS p WHERE p.termodebusca LIKE %:t% LIMIT 50", nativeQuery = true)
    Set<Person> findByTerm(@Param("t") String t);

    boolean existsByApelido(String apelido);

}
