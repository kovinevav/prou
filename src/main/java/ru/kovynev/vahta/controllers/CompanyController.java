package ru.kovynev.vahta.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.entity.Speciality;
import ru.kovynev.vahta.entity.Vacancy;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;
import ru.kovynev.vahta.rep.VacanciesRepository;

import java.util.List;


@Controller
@AllArgsConstructor
@Log4j2
@RequestMapping("/companies")
public class CompanyController {
    private final
    CompanyRepository companyRepository;
    private final
    VacanciesRepository vacanciesRepository;
    private final
    ReviewRepository reviewRepository;


    @GetMapping("/{id}")
    public String showCompany(@PathVariable(value = "id") long id, Model model) {
//TODO Вынести логику в сервис
        Company company = companyRepository.findById(id).orElseThrow();
        Iterable<Review> reviews = reviewRepository.findByCompany(company);
        Iterable<Vacancy> vacancies = vacanciesRepository.findByCompany(company);

        String pathToPhoto = ("${images.company}") + company.getId() + ".jpg";
        model.addAttribute("pathToPhoto", pathToPhoto);

        List<Review> reviewList = (List<Review>) reviews;
        if (reviewList.isEmpty()) {
            Review review = new Review();
            review.setText("Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.");
            ((List<Review>) reviews).add(review);
        }

        List<Vacancy> vacancyList = (List<Vacancy>) vacancies;
        if (vacancyList.isEmpty()) {
            Vacancy vacancy = new Vacancy();
            Speciality speciality = new Speciality();
            speciality.setName("Вакансии по данной компании отсутствуют.");
            vacancy.setSpeciality(speciality);
            ((List<Vacancy>) vacancies).add(vacancy);
        }
        model.addAttribute("company", company);
        model.addAttribute("reviews", reviews);
        model.addAttribute("vacancies", vacancies);
        return "companies/company";
    }

    @GetMapping("")
    public String showAll(Model model) {
        log.info("Show all companies");
        Iterable<Company> companies = companyRepository.findAll();

        model.addAttribute("companies", companies);
        return "companies/all_companies";
    }
}
