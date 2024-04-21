package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Vacancy;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.VacanciesRepository;

import java.util.Date;

@Controller
public class VacancyController {
    final
    VacanciesRepository vacanciesRepository;
    private final CompanyRepository companyRepository;
    Logger logger = LogManager.getLogger("VacancyController.class");

    public VacancyController(VacanciesRepository vacanciesRepository, CompanyRepository companyRepository) {
        this.vacanciesRepository = vacanciesRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("vacancies")
    public String showAll(Model model){
        model.addAttribute("vacancies", vacanciesRepository.findAll());

        return "vacancies/all_vacancies";
    }

    @GetMapping("vacancies/{id}")
    public String show(@PathVariable("id") Long id, Model model){
        model.addAttribute("vacancy", vacanciesRepository.findById(id).orElseThrow());

        Vacancy vacancy = vacanciesRepository.findById(id).orElseThrow();
       /* String nameOfCompany = vacancy.getCompany().getName();
        System.out.println(nameOfCompany);*/

        return "vacancies/"+id;
    }
}
