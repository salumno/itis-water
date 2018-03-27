package ru.kpfu.itis.water.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsAddForm {
    private String title;
    private String text;
    private MultipartFile[] files;
}
