package ru.kovynev.vahta.rep;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from  Company c order by 'id' desc limit 2")
    Company findById(Company company); //must have 1
    @Query("select c from  Company c order by 'id' desc limit 2")
    Iterable<Company> findAllByOrderByIdDesc(); //must have 2









}
