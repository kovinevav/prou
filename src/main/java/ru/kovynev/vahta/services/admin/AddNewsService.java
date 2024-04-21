package ru.kovynev.vahta.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.News;
import ru.kovynev.vahta.rep.NewsRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Qualifier("addNews")
public class AddNewsService implements AddNewsServiceInterface {
    final
    NewsRepository newsRepository;

    public AddNewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void addNews(MultipartFile file, News news) {
        newsRepository.save(news);
        System.out.println("id = " + news.getId());
        try {
            if (file != null)
                Files.copy(file.getInputStream(), Path.of("/home/distr/images/news/" + news.getId() + ".jpg"));

            System.out.println("Copy was successful");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
