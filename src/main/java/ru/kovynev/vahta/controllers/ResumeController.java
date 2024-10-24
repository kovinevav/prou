package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kovynev.vahta.rep.UserRepository;

@Controller
@AllArgsConstructor
@Log4j2
public class ResumeController {
    private final UserRepository userRepository;

    @GetMapping("/resume")
    public String showResumePage(Model model) {
        log.info("Вызван резюме контроллер. Показать все активные резюме");
        model.addAttribute("users", userRepository.findUserEntitiesByPublication(true));

        return "resume/all_resume";
    }
}