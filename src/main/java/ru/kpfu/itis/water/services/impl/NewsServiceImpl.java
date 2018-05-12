package ru.kpfu.itis.water.services.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.water.dto.NewsDto;
import ru.kpfu.itis.water.form.NewsAddForm;
import ru.kpfu.itis.water.model.FileInfo;
import ru.kpfu.itis.water.model.News;
import ru.kpfu.itis.water.model.NewsImage;
import ru.kpfu.itis.water.repositories.NewsRepository;
import ru.kpfu.itis.water.services.NewsService;
import ru.kpfu.itis.water.util.FileStorageUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;
    private FileStorageUtil fileStorageUtil;

    public NewsServiceImpl(NewsRepository newsRepository, FileStorageUtil fileStorageUtil) {
        this.newsRepository = newsRepository;
        this.fileStorageUtil = fileStorageUtil;
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getLastNews(long count) {
        return newsRepository.findLatestNews(count);
    }

    @Override
    public List<NewsDto> getAllDtoNews() {
        return getAllNews().stream().map(NewsDto::createOnNews).collect(Collectors.toList());
    }

    @Override
    public void addBrandNewNews(NewsAddForm newsAddingForm) {
        News news = News.builder()
                .date(Date.valueOf(LocalDate.now()))
                .text(newsAddingForm.getText())
                .title(newsAddingForm.getTitle())
                .build();
        List<NewsImage> attachments = createNewsAttachments(newsAddingForm.getFiles(), news);
        news.setNewsImages(attachments);
        newsRepository.save(news);
    }

    @Override
    public void removeNewsById(Long newsId) {
        newsRepository.delete(newsId);
    }

    private List<NewsImage> createNewsAttachments(MultipartFile[] files, News news) {
        List<NewsImage> attachments = Lists.newArrayList();
        if (files != null) {
            for (MultipartFile file: files) {
                FileInfo fileInfo = fileStorageUtil.getNewsImageInfoByMultipart(file);
                NewsImage newsImage = NewsImage.builder().fileInfo(fileInfo).news(news).build();
                attachments.add(newsImage);
                fileStorageUtil.saveNewsImageFileToStorage(file, newsImage);
            }
        }
        return attachments;
    }

}
