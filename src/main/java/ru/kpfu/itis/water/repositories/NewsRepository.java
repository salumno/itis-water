package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.News;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface NewsRepository extends JpaRepository<News, Long>{
}
