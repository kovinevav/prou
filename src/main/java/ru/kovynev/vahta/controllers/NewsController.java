package ru.kovynev.vahta.controllers;

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
@RequestMapping("/news")
public class NewsController{
    final
    NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("")
    public String showAll(Model model) {
        Iterable<News> companies = newsRepository.findAll();
        model.addAttribute("companies", companies);
        return "/news/all_news";
    }
    @GetMapping("/{id}")
    public String shownews(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        model.addAttribute("news", news);

        return "news/news";
    }


}
