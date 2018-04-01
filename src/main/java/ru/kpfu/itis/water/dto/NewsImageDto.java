package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.FileInfo;
import ru.kpfu.itis.water.model.NewsImage;

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
public class NewsImageDto {
    private Long id;

    private FileInfo fileInfo;

    private Long newsId;

    private NewsImageDto(NewsImage newsImage) {
        id = newsImage.getId();
        fileInfo = newsImage.getFileInfo();
        newsId = newsImage.getNews().getId();
    }

    public static NewsImageDto createOnNewsImage(NewsImage newsImage) {
        return new NewsImageDto(newsImage);
    }
}
