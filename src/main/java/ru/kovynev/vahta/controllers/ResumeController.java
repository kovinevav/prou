package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kovynev.vahta.rep.UserRepository;

@Controller
@AllArgsConstructor
@Log4j2
public class ResumeController {
    private final UserRepository userRepository;

    @GetMapping("/resume")
    public String showResumePage(Model model) {
        log.info("Вызван резюме контроллер. Показать все активные резюме");
        model.addAttribute("resumes", userRepository.findUserEntitiesByPublication(true));

        return "resume/all_resume";
    }
    @GetMapping("/resume/{id}")
    public String show(Model model, @PathVariable(value = "id") long id) {
        log.info("Вызван резюме контроллер. Показать резюме по id");
        model.addAttribute("resume", userRepository.findById(id).orElseThrow());

        return "resume/resume";
    }
}