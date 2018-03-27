package ru.kpfu.itis.water.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@NoArgsConstructor

@Entity
@DiscriminatorValue("news")
public class NewsImage extends Image {

    @Builder
    private NewsImage(Long id, FileInfo fileInfo, News news) {
        super(id, fileInfo);
        this.news = news;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
}
