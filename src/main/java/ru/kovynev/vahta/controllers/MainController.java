package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.CompanyRepository;
import ru.kovynev.vahta.rep.UserRepository;
import ru.kovynev.vahta.services.UserEntityService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@Log4j2
public class MainController {
    private final
    CompanyRepository companyRepository;
    private final
    UserRepository userRepository;
    private final
    UserEntityService userEntityService;


    @GetMapping("/")
    public String index(Model model) throws Exception {
        Iterable<Company> companies = companyRepository.findAll(Pageable.ofSize(18));
        model.addAttribute("companies", companies);
        log.info("Start of program");
        userEntityService.getCurrentUserEntity();
        return "index";
    }
}
