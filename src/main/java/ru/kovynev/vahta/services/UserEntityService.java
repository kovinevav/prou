package ru.kovynev.vahta.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@PropertySource("classpath:properties/paths.properties")
public class UserEntityService {
    private final UserRepository userRepository;
    @Value("${image.people}")
    private String folder;

    public UserEntity getCurrentUserEntity() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String login = userDetails.getUsername();
            Optional<UserEntity> userEntityOptional = userRepository.findByUsername(login);
            return userEntityOptional.get();
        } catch (Exception e) {
            log.info("Пользователь не авторизован");
            return new UserEntity();
        }

    }

    public String putUserEntity(Model model) throws Exception {

        UserEntity userEntity = getCurrentUserEntity();
        log.info("Пользователь авторизован. Переходим на персональную страницу");
        model.addAttribute("userEntity", userEntity);
       // model.addAttribute("pathToPhoto", "/images/people/" + userEntity.getId() + ".jpg");

        model.addAttribute("pathToPhoto", folder + userEntity.getId() + ".jpg");
        return "personalpage/personalpage";

    }

}
