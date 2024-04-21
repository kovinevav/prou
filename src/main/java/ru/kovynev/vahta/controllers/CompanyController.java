package ru.kovynev.vahta.controllers;



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
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    VacanciesRepository vacanciesRepository;
    @Autowired
    ReviewRepository reviewRepository;
    Logger logger = LogManager.getLogger("CompanyController.class");


    @GetMapping("/{id}")
    public String showCompany(@PathVariable(value = "id") long id, Model model) {

        Company company = companyRepository.findById(id).orElseThrow();
        Iterable<Review> reviews = reviewRepository.findByCompany(company);
        Iterable<Vacancy> vacancies = vacanciesRepository.findByCompany(company);



        String pathToPhoto = "/images/company/" + company.getId() + ".jpg";
        model.addAttribute("pathToPhoto", pathToPhoto);

        List<Review> reviewList = (List<Review>) reviews;
        if(reviewList.isEmpty()){
        Review review =new Review();
        review.setText("Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.");
        ((List<Review>) reviews).add(review);
        }

        List<Vacancy> vacancyList = (List<Vacancy>) vacancies;
        if(vacancyList.isEmpty()){
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
        logger.info("Show all companies");
        Iterable<Company> companies = companyRepository.findAll();
        //Iterable<Company> companies = companyRepository.findAllByOrderByIdDesc(Pageable.ofSize(16));

        model.addAttribute("companies", companies);
        return "companies/all_companies";
    }
}
