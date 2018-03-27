package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.dto.NewsDto;
import ru.kpfu.itis.water.form.NewsAddForm;
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
}
