package ru.kpfu.itis.water.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.water.model.News;
import ru.kpfu.itis.water.repositories.NewsRepository;
import ru.kpfu.itis.water.services.NewsService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
