package ru.kpfu.itis.water.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "news", cascade = CascadeType.ALL)
    private List<NewsImage> newsImages;

}
