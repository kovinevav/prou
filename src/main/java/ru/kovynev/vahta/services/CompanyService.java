package ru.kovynev.vahta.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.entity.Speciality;
import ru.kovynev.vahta.entity.Vacancy;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;
import ru.kovynev.vahta.rep.VacanciesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyService {
    private final
    CompanyRepository companyRepository;
    private final
    ReviewRepository reviewRepository;
    private final
    VacanciesRepository vacanciesRepository;


    public Company getCompanyByID(long id) {
        log.info("CompanyService Method getCompanyById");
        return companyRepository.findById(id).orElseThrow();
    }

    public List<Review> getReviewsByCompany(Company company) {
        Iterable<Review> reviews = reviewRepository.findByCompany(company);
        List<Review> reviewList = (List<Review>) reviews;
        if (reviewList.isEmpty()) {
            Review review = new Review();
            review.setText("Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.");
            ((List<Review>) reviews).add(review);
        }
        log.info("CompanyService Method getReviewByCompany");
        return reviewList;
    }

    public List<Vacancy> getVacanciesByCompany(Company company) {
        Iterable<Vacancy> vacancies = vacanciesRepository.findByCompany(company);
        List<Vacancy> vacancyList = (List<Vacancy>) vacancies;
        if (vacancyList.isEmpty()) {
            Vacancy vacancy = new Vacancy();
            Speciality speciality = new Speciality();
            speciality.setName("Вакансии по данной компании отсутствуют.");
            vacancy.setSpeciality(speciality);
            ((List<Vacancy>) vacancies).add(vacancy);
        }
        log.info("CompanyService Method getVacanciesByCompany");
        return vacancyList;
    }

    public List<Company> getAllCompanies(){
        log.info("CompanyService Method getAllCompanies");
        return companyRepository.findAll();
    }


}
