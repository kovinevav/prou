package ru.kovynev.vahta.services.admin;

import org.springframework.web.multipart.MultipartFile;

public interface AddNewsService {
    void addNews(int id, MultipartFile file, String str);
}
