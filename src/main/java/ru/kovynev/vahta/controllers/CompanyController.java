package ru.kovynev.vahta.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.services.CompanyService;

import java.util.List;


@Controller
@AllArgsConstructor
@Log4j2
@RequestMapping("/companies")
public class CompanyController {

    private final
    CompanyService companyService;


    @GetMapping("/{id}")
    public String showCompany(@PathVariable(value = "id") long id, Model model) {
        Company company = companyService.getCompanyByID(id);
        String pathToPhoto = ("${images.company}") + company.getId() + ".jpg";
        model.addAttribute("pathToPhoto", pathToPhoto);
        model.addAttribute("company", company);
        model.addAttribute("reviews", companyService.getReviewsByCompany(company));
        model.addAttribute("vacancies", companyService.getVacanciesByCompany(company));
        return "companies/company";
    }

    @GetMapping("")
    public String showAll(Model model) {
        Iterable<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "companies/all_companies";
    }
}
