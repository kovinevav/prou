package ru.kovynev.vahta.controllers.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.Role;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.RoleRepository;
import ru.kovynev.vahta.rep.UserRepository;
import ru.kovynev.vahta.services.EditPersonalPageService;
import ru.kovynev.vahta.services.UserEntityService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final  UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EditPersonalPageService editPersonalPageService;
    private final UserEntityService userEntityService;


@GetMapping("/all_users")
    public String showUsers(Model model){
        Iterable<UserEntity> users = userRepository.findAll();
        model.addAttribute("users", users);
        log.info("Started AdminUserController/ShowUsers");
        return "admin/users/all_users";
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        UserEntity user = userRepository.findById(id).orElseThrow();
        user.clearRoles();
        userRepository.delete(user);
        return "admin/users/all_users";
    }

    @GetMapping("/users/new")
    public String newUser(Model model){
        model.addAttribute("userEntity", editPersonalPageService.createFakeUser());
        return "admin/users/new_user";
    }

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id){
    UserEntity userEntity = userRepository.findById(id).orElseThrow();
    model.addAttribute("userEntity", userEntity);
    model.addAttribute("pathToPhoto", "/images/users/" + userEntity.getId() + ".jpg");
    return "admin/users/edit";
    }

    @PatchMapping("/editperson")
    public String patchPerson(@ModelAttribute("userEntity") UserEntity user,
                              @ModelAttribute("file") MultipartFile file) {
        //userEntityService.update(user, file);
        log.info("AdminUserController: /editperson");
        return "redirect:/admin/all_users";
        //TODO Разобраться с редиректом
    }
}
