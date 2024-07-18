package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kovynev.vahta.services.FindService;

@Controller
@AllArgsConstructor
@Log4j2
public class FindController {
    public final FindService findService;

    @GetMapping("/find")
    public String findSomethingByNameOrAnons(@ModelAttribute("nameOfCompany") String nameOfCompany, Model model) {
        log.info("Request was made for the word {}", nameOfCompany);
        model.addAttribute("companies", findService.findCompanyByNameOrAnons(nameOfCompany));
        return "index";
    }
}
