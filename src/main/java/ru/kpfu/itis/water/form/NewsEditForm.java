package ru.kpfu.itis.water.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsEditForm {
    private String title;
    private String text;
}
