package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.News;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

public class NewsDto {
    private Long id;

    private String title;

    private String text;

    private Date date;

    private List<NewsImageDto> newsImages;

    private NewsDto(News news) {
        id = news.getId();
        title = news.getTitle();
        text = news.getText();
        date = news.getDate();
        newsImages = news.getNewsImages().stream()
                .map(NewsImageDto::createOnNewsImage)
                .collect(Collectors.toList());
    }

    public static NewsDto createOnNews(News news) {
        return new NewsDto(news);
    }
}
