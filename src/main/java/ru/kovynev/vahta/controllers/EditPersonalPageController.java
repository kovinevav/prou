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
public class EditPersonalPageController {
    private final UserEntityService userEntityService;
    private final EditPersonalPageService editPersonalPageService;

    @GetMapping("/editpersonalpage")
    public String showPage(Model model) throws Exception {
        log.info("Came to PersonalPageController");
        userEntityService.putUserEntity(model);
        return "personalPage/editPersonalPage";
    }


    @PatchMapping("/editpersonalpage")
    public String patchPersonalPage(@ModelAttribute("userEntity") UserEntity userEntity, @ModelAttribute("file") MultipartFile file) {
        log.info("Do patch in PersonalPageController");
        log.info(userEntity.getName());
        log.info(userEntity.getRoles());
        log.info(userEntity.getAbout());
        editPersonalPageService.resaveUserEntity(userEntity, file);
        return "personalPage/editPersonalPage";
    }
}

