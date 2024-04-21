package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalPageController {
    Logger logger = LogManager.getLogger("PersonalPageController.class");


    @GetMapping("/personalpage")
    public String showPage(Model model, SecurityContextHolder holder){
        model.addAttribute("holder", holder);


        return "personalpage/personalpage";
    }
}

