package ru.kovynev.vahta.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kovynev.vahta.entity.Company;
import ru.kovynev.vahta.entity.News;
import ru.kovynev.vahta.entity.Review;
import ru.kovynev.vahta.rep.NewsRepository;

@Controller
@AllArgsConstructor
@Log4j2
@RequestMapping("/news")
public class NewsController {
    private final
    NewsRepository newsRepository;

    @GetMapping("")
    public String showAll(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news/all_news";
    }

    @GetMapping("/{id}")
    public String shownews(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        model.addAttribute("news", news);
        String pathToPhoto = "/images/news/" + news.getId() + ".jpg";
        model.addAttribute("pathToPhoto", pathToPhoto);
        return "news/news";
    }
}
