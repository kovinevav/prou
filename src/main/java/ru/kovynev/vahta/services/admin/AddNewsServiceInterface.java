package ru.kovynev.vahta.services.admin;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.News;

public interface AddNewsServiceInterface {
    void addNews(MultipartFile file, News news);

}
