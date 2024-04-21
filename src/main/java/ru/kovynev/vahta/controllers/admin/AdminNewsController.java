package ru.kovynev.vahta.controllers.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kovynev.vahta.entity.News;
import ru.kovynev.vahta.rep.NewsRepository;
import ru.kovynev.vahta.services.admin.AddNewsService;
import ru.kovynev.vahta.services.admin.AddNewsServiceInterface;

@Controller
public class AdminNewsController {
    Logger logger = LogManager.getLogger();
    final NewsRepository newsRepository;
    @Qualifier("addNews")
    final AddNewsServiceInterface addNewsService;

    public AdminNewsController(NewsRepository newsRepository, AddNewsServiceInterface addNewsService) {
        this.newsRepository = newsRepository;
        this.addNewsService = addNewsService;
    }

    @GetMapping("admin/news")
    public String showNews(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "admin/news/all_news";
    }


    @GetMapping("admin/news/new/{id}")
    public String addNews(Model model,
                          @PathVariable("id") long id) {
        News news = new News();
        model.addAttribute("news", news);
        return "admin/news/new_news";
    }


    @DeleteMapping("admin/news/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);
        return "redirect:/admin/news";
    }

    @PatchMapping("admin/news/{id}")
    public String update(@PathVariable(value = "id") long id, @ModelAttribute("news") News news) {
        news.setId(id);
        newsRepository.save(news);
        return "redirect:/admin/news";
    }

    @GetMapping("admin/news/{id}/edit")
    public String editVacancy(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepository.findById(id).orElseThrow();
        model.addAttribute("news", news);
        return "admin/news/edit_news";
    }

    @GetMapping("admin/news/new")
    public String createNewNews(Model model) {
        model.addAttribute("news", new News());
        return "admin/news/new_news";
    }


    //Принимаем и сохраняем новость
    @PostMapping("admin/news")
    public String createNews(@ModelAttribute("news") News news,
                             @ModelAttribute("file") MultipartFile file) {
        addNewsService.addNews(file, news);
        return "redirect:/admin/news";
    }

}
