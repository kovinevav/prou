package ru.kovynev.vahta.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    final
    UserRepository userRepository;
    Logger logger = LogManager.getLogger();

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
@GetMapping("/all_users")
    public String showUsers(Model model){
        Iterable<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        logger.info("Started AdminUserController/ShowUsers");
        return "admin/users/all_users";
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        UserEntity user = userRepository.findById(id).orElseThrow();
        System.out.println(user.getName());
        userRepository.delete(user);
        return "admin/users/all_users";
    }
}
