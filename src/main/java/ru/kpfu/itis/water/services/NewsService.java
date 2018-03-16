package ru.kpfu.itis.water.services;

import ru.kpfu.itis.water.model.News;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface NewsService {
    List<News> getAllNews();
}
