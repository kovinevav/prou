package ru.kovynev.vahta.rep;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Iterable<Review> findByCompany(Company company);

    List<Review> findAllByOrderByIdDesc();
}
