package ru.kovynev.vahta.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovynev.vahta.entity.Letter;
@Repository
public interface LetterRepository extends CrudRepository<Letter, Long> {

}
