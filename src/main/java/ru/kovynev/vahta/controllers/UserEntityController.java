package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.services.UserEntityService;

@Controller
public class UserEntityController {
    Logger logger = LogManager.getLogger("PersonalPageController.class");
    final UserEntityService userEntityService;

    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/personal_page")
    public String showPersonalPage(Model model) {


        return "personal/personal_page";
    }
}
