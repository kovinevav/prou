package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.rep.CompanyRepository;

import java.io.File;
import java.util.List;

@Controller
public class MainController {
@Autowired
CompanyRepository companyRepository;
Logger logger = LogManager.getLogger(MainController.class);

    @GetMapping("/")
    public String index(Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Iterable<Company> companies = companyRepository.findAll(Pageable.ofSize(18));

        model.addAttribute("companies", companies);
        logger.info("Start of program");
        return "index";
    }
}
