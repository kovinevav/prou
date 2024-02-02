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
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;

import java.util.List;


@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ReviewRepository reviewRepository;
    Logger logger = LogManager.getLogger();


    @GetMapping("/{id}")
    public String showcompany(@PathVariable(value = "id") long id, Model model) {

        Company company = companyRepository.findById(id).orElseThrow();
        Iterable<Review> reviews = reviewRepository.findByCompany(company);
        model.addAttribute("company", company);
        model.addAttribute("reviews", reviews);
        String pathToPhoto = "/home/distr/images/company/" + company.getId() + ".jpg";
        model.addAttribute("pathToPhoto", pathToPhoto);
        List<Review> reviewList = (List<Review>) reviews;
        if(reviewList.isEmpty()){
        Review review =new Review();
        review.setText("Отзывы по данной компании отсутствуют. Ваш отзыв может стать первым.");
        ((List<Review>) reviews).add(review);
        }

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
