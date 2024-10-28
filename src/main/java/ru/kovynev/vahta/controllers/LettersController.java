package ru.kovynev.vahta.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovynev.vahta.entity.Letter;

@Controller
@Log4j2
@RequestMapping("/letters")
public class LettersController {

    @GetMapping("new")
    public String newLetter(Model model) {
        model.addAttribute("letter", new Letter());
        return "letters/new";
    }
    @PostMapping
    public String sendLetter(@ModelAttribute("letter") Letter letter, Model model) {

        return "letters/new";
    }

}
