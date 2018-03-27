package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.services.NewsService;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
public class MainPageController {

    private NewsService newsService;

    public MainPageController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(@ModelAttribute("model")ModelMap model) {
        model.addAttribute("news", newsService.getLastNews(3));
        return "main-page";
    }
}