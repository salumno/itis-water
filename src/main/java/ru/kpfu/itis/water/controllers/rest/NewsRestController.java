package ru.kpfu.itis.water.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.water.dto.NewsDto;
import ru.kpfu.itis.water.dto.NewsImageDto;
import ru.kpfu.itis.water.form.NewsAddForm;
import ru.kpfu.itis.water.form.NewsEditForm;
import ru.kpfu.itis.water.form.NewsImageEditForm;
import ru.kpfu.itis.water.services.NewsService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping(value = "/api/news")
public class NewsRestController {
    private NewsService newsService;

    public NewsRestController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<NewsDto> getAllNews() {
        return newsService.getAllDtoNews();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "content-type=multipart/*")
    public List<NewsDto> addNews(@ModelAttribute NewsAddForm newsAddForm) {
        newsService.addBrandNewNews(newsAddForm);
        return newsService.getAllDtoNews();
    }

    @RequestMapping(value = "/{newsId}", method = RequestMethod.DELETE)
    public List<NewsDto> deleteNews(@PathVariable("newsId") Long newsId) {
        newsService.removeNewsById(newsId);
        return newsService.getAllDtoNews();
    }

    @PostMapping("/{id}/update")
    public void editNews(@ModelAttribute NewsEditForm form,
                         @PathVariable("id") Long newsId) {
        newsService.editNewsById(newsId, form);
    }

    @PostMapping("/{id}/image")
    public List<NewsImageDto> addImageToNews(@ModelAttribute NewsImageEditForm form,
                                             @PathVariable("id") Long newsId) {
        newsService.addImagesToNews(newsId, form);
        return newsService.getImagesDtoByNewsId(newsId);
    }

    @PostMapping("/{newsId}/image/{imageId}/delete")
    public List<NewsImageDto> deleteNewsImage(@PathVariable("newsId") Long newsId, @PathVariable("imageId") Long imageId) {
        newsService.deleteNewsImage(newsId, imageId);
        return newsService.getImagesDtoByNewsId(newsId);
    }

}
