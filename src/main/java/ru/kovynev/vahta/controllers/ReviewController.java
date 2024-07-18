package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;


@Controller
@AllArgsConstructor
@Log4j2
public class ReviewController {
    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;


    @PostMapping("review")
    public String SendReview(@ModelAttribute("name") String name,
                             @ModelAttribute("review") String text,
                             @ModelAttribute("id") Long id) {
        log.info("start review controller");
        Review review = new Review();
        review.setText(text);
        review.setName(name);
        Company company = companyRepository.findById(id).orElseThrow();
        review.setCompany(company);
        reviewRepository.save(review);
        return "redirect:companies/" + id;

    }
}
