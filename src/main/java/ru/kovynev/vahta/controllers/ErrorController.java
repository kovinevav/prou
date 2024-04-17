package ru.kovynev.vahta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Code404Controller {
   @RequestMapping("/error")
    public String getCode404(){
        return "redirect:/";
    }
}
