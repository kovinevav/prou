package ru.kovynev.vahta.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.Role;
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.RoleRepository;
import ru.kovynev.vahta.rep.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;


@Service
@RequiredArgsConstructor
@Log4j2
@PropertySource("classpath:properties/paths.properties")
public class EditPersonalPageService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Value("/home/distr/images/users/")
    String folder;

    public void resaveUserEntity(UserEntity userEntity, MultipartFile file) {
        UserEntity chanchedUserEntity = userRepository.getReferenceById(userEntity.getId());
        chanchedUserEntity.setName(userEntity.getName());
        chanchedUserEntity.setUsername(userEntity.getUsername());
        chanchedUserEntity.setSpeciality(userEntity.getSpeciality());
        chanchedUserEntity.setAbout(userEntity.getAbout());
        try {
            log.info("Try to resave userEntity");
            Files.copy(file.getInputStream(), Path.of(folder + userEntity.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
            log.info("Копирование фото прошло успешно");
        } catch (IOException e) {
            log.info("Ошибка в процессе копирования фото");
            throw new RuntimeException(e);
        }
    }

    public UserEntity createFakeUser() {
        UserEntity newUser = new UserEntity();
        Role role = roleRepository.findByName("ROLE_USER").get();
        newUser.setRoles(Collections.singletonList(role));
        newUser.setUsername("fake@mail.ru");
        userRepository.save(newUser);
        return newUser;
    }

}
