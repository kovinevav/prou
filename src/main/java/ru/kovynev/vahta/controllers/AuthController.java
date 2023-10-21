package ru.kovynev.vahta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kovynev.vahta.dto.LoginDto;
import ru.kovynev.vahta.dto.RegisterDto;
import ru.kovynev.vahta.entity.Role;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.RoleRepository;
import ru.kovynev.vahta.rep.UserRepository;

import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        System.out.println("GET_LOGIN");
        model.addAttribute("loginDto", new LoginDto());
        return "/auth/login";
    }


    @PostMapping(value = "/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto) {
        System.out.println("POST_LOGIN");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:index";

    }


    @PostMapping("/register")
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return "/auth/login";
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return "redirect: /personalpage";
    }

    @GetMapping("/register")
    public String showFormRegister(Model model){
        model.addAttribute("registerDto", new RegisterDto());
        return "/auth/registration";
    }




}
