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
import ru.kovynev.vahta.entity.UserEntity;
import ru.kovynev.vahta.rep.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Service
@RequiredArgsConstructor
@Log4j2
@PropertySource("classpath:properties/paths.properties")
public class EditPersonalPageService {
    private final UserRepository userRepository;
    @Value("${image.people}")
    String folder;

    public void resaveUserEntity(UserEntity userEntity, MultipartFile file) {
        UserEntity chanchedUserEntity = userRepository.getReferenceById(userEntity.getId());
        chanchedUserEntity.setName(userEntity.getName());
        chanchedUserEntity.setUsername(userEntity.getUsername());
        chanchedUserEntity.setSpeciality(userEntity.getSpeciality());
        chanchedUserEntity.setAbout(userEntity.getAbout());

        try {
            log.info("Try to resave userEntity");
            if (!file.isEmpty()) {
                log.info("Name of folder: {}", folder);
                File directory = new File(folder);
                if(!directory.exists()) {
                    directory.mkdir();
                    log.info("Created new folder: {}", directory.getAbsolutePath());
                }

                Files.copy(file.getInputStream(), Path.of(folder + userEntity.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
            }
            log.info("Копирование фото прошло успешно");
        } catch (IOException e) {
            log.info("Ошибка в процессе копирования фото");
            throw new RuntimeException(e);
        }

    }
}
