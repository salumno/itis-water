package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.dto.NewsDto;
import ru.kpfu.itis.water.dto.NewsImageDto;
import ru.kpfu.itis.water.form.NewsAddForm;
import ru.kpfu.itis.water.form.NewsEditForm;
import ru.kpfu.itis.water.form.NewsImageEditForm;
import ru.kpfu.itis.water.model.News;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface NewsService {
    List<News> getAllNews();

    List<News> getLastNews(long count);

    List<NewsDto> getAllDtoNews();

    void addBrandNewNews(NewsAddForm newsAddingForm);

    void removeNewsById(Long newsId);

    News getNewsById(Long newsId);

    NewsDto getNewsDtoById(Long newsId);

    void addImagesToNews(Long newsId, NewsImageEditForm form);

    List<NewsImageDto> getImagesDtoByNewsId(Long newsId);

    void deleteNewsImage(Long newsId, Long imageId);

    void editNewsById(Long newsId, NewsEditForm form);
}
