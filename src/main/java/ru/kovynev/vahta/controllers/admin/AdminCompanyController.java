package ru.kovynev.vahta.controllers.admin;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.ReviewRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Controller
@RequestMapping("/admin/companies")
public class AdminCompanyController {
    final
    CompanyRepository companyRepository;
    final
    ReviewRepository reviewRepository;
    Logger logger = LogManager.getLogger(AdminCompanyController.class);

    public AdminCompanyController(CompanyRepository companyRepository, ReviewRepository reviewRepository) {
        this.companyRepository = companyRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/new")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "admin/companies/add_company";
    }

    @PostMapping("")
    public String addCompany(@ModelAttribute("company") Company company,
                             @ModelAttribute("file") MultipartFile file)
                             throws IOException {
        companyRepository.save(company);
        Files.copy(file.getInputStream(), Path.of("/images/company/" + company.getId() + ".jpg"));
        return "redirect:/administrator";
    }

    @GetMapping("/{id}/edit")
    public String editCompany(@PathVariable(value = "id") long id, Model model) {
        Company company = companyRepository.findById(id).orElseThrow();
        model.addAttribute("company", company);

        return "admin/companies/edit_company";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable(value = "id") long id,
                         @ModelAttribute("company") Company company,
                         @ModelAttribute("file") MultipartFile file)
                         throws IOException {


        company.setId(id);
        companyRepository.save(company);
        try {
            Files.copy(file.getInputStream(), Path.of("/images/company/" + company.getId() + ".jpg"));
        } catch (Exception e) {
            System.out.println("Ошибка при копировании логотипа компании на сервер");
        }
        System.out.println("OK");
        return "redirect:/administrator";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") long id, @ModelAttribute("company") Company updatedcompany) {
        Company company = companyRepository.findById(id).orElseThrow();
        companyRepository.delete(company);
        return "redirect:/administrator";
    }


}
