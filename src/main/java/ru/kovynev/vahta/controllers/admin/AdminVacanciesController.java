package ru.kovynev.vahta.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Speciality;
import ru.kovynev.vahta.entity.Vacancy;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.SpecialityPepository;
import ru.kovynev.vahta.rep.VacanciesRepository;

import java.util.Date;
import java.util.List;

@Controller
public class AdminVacanciesController {
    Logger logger = LogManager.getLogger();
    final
    VacanciesRepository vacanciesRepository;
    final
    CompanyRepository companyRepository;
    final SpecialityPepository specialityPepository;

    public AdminVacanciesController(CompanyRepository companyRepository, VacanciesRepository vacanciesRepository, SpecialityPepository specialityPepository) {
        this.companyRepository = companyRepository;
        this.vacanciesRepository = vacanciesRepository;
        this.specialityPepository = specialityPepository;
    }


    @GetMapping("admin/vacancies")
    public String showvacancies(Model model) {
        Iterable<Vacancy> vacancies = vacanciesRepository.findAll();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("companies", companyRepository);
        return "admin/vacancies/all_vacancies";
    }


    @GetMapping("/admin/vacancies/new")
    public String newVacancy(Model model) {
        logger.info("method 'New vacancy' started");
        Vacancy vacancy = new Vacancy();
        List<Company> companies = companyRepository.findAll();
        Iterable<Speciality> specialities = specialityPepository.findAll();

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("companies", companies);
        model.addAttribute("specialities", specialities);

        logger.info("method 'New vacancy' finished");
        return "admin/vacancies/new";
    }

    @PostMapping("admin/vacancies")
    public String createVacancy(@ModelAttribute("vacancy") Vacancy vacancy) {
        vacancy.setDate(new Date());
        vacanciesRepository.save(vacancy);
       /* Company company = vacancy.getCompany();
        company.setVacancy(vacancy);
        companyRepository.save(company);*/
        return "redirect:/admin/vacancies";
    }

    @DeleteMapping("admin/vacancies/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        Vacancy vacancy = vacanciesRepository.findById(id).orElseThrow();
        vacanciesRepository.delete(vacancy);
        return "redirect:/admin/vacancies";
    }

    @PatchMapping("admin/vacancies/{id}")
    public String update(@PathVariable(value = "id") long id, @ModelAttribute("vacancy") Vacancy vacancy) {
        vacancy.setId(id);
        vacanciesRepository.save(vacancy);
        return "redirect:/admin/vacancies";
    }

    @GetMapping("admin/vacancies/{id}/edit")
    public String editVacancy(@PathVariable(value = "id") long id, Model model) {
        Vacancy vacancy = vacanciesRepository.findById(id).orElseThrow();
        model.addAttribute("vacancy", vacancy);
        return "admin/vacancies/edit_vacancy";
    }
}

