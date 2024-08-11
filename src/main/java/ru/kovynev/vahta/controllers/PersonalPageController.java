package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.services.EditPersonalPageService;
import ru.kovynev.vahta.services.UserEntityService;

@Controller
@AllArgsConstructor
@Log4j2
public class PersonalPageController {
    private final UserEntityService userEntityService;
    //private final EditPersonalPageService editPersonalPageService;

    @GetMapping("/personalpage")
    public String showPage(Model model) throws Exception {
        log.info("Came to PersonalPageController");
        userEntityService.putUserEntity(model);
        return "personalPage/personalPage";
    }


    @GetMapping("/personalpage/messages")
    public String showMessages(Model model) throws Exception {


        return "/personalPage/messages";
    }

    @PatchMapping("/editpersonalpage")
    public String patchPersonalDate(@ModelAttribute("userEntity") UserEntity user,
                                    @ModelAttribute("file") MultipartFile file) {
        log.info("PersonalPageController: /editPersonalPage");
        userEntityService.update(user, file);
        return "personalPage/personalpage";
    }


}

