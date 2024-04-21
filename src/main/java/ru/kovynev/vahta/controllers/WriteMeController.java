package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovynev.vahta.entity.Letter;
import ru.kovynev.vahta.rep.LetterRepository;

@Controller
@RequestMapping("/writeme")

public class WriteMeController {
    final
    LetterRepository letterRepository;
    Logger logger = LogManager.getLogger("WriteMeController.class");

    public WriteMeController(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @GetMapping()
    public String showWriter(Model model) {
model.addAttribute("letter", new Letter());
        System.out.println("Writer");

        return "writeme/writeme";
    }

    @PostMapping()
    public String getterOfLetter(@ModelAttribute("letter") Letter letter) {
       letterRepository.save(letter);

        return "writeme/success";
    }
}
