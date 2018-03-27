package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsPage() {
        return "admin/news-page";
    }
}
