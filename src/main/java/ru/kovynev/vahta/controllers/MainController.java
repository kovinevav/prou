package ru.kovynev.vahta.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.Optional;

@Controller
public class MainController {
    final
    CompanyRepository companyRepository;
    Logger logger = LogManager.getLogger(MainController.class);
    final
    UserRepository userRepository;


    public MainController(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Iterable<Company> companies = companyRepository.findAll(Pageable.ofSize(18));

        model.addAttribute("companies", companies);
        logger.info("Start of program");

       /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String login = userDetails.getUsername();
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(login);
        UserEntity userEntity = userEntityOptional.get();*/

       /* userEntity.setName("Andrew");
        userRepository.save(userEntity);
        System.out.println(userEntity.getName());*/


        return "index";
    }
}
