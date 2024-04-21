package ru.kovynev.vahta.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Vacancy;
@Repository
public interface VacanciesRepository extends CrudRepository<Vacancy, Long> {
    Iterable<Vacancy> findByCompany(Company company);
}
