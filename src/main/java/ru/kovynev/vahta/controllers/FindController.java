package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kovynev.vahta.services.FindService;

@Controller
public class FindController {
    final FindService findService;
    Logger logger = LogManager.getLogger("FindController.class");

    public FindController(FindService findService) {
        this.findService = findService;
    }


    @GetMapping("/find")
    public String findSomethingByNameOrAnons(@ModelAttribute("nameOfCompany") String nameOfCompany, Model model){
        logger.info("Request was made for the word " + nameOfCompany);
    model.addAttribute("companies", findService.findCompanyByNameOrAnons(nameOfCompany));
      return "index";
    }
}
