package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.water.model.News;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface NewsRepository extends JpaRepository<News, Long>{

    @Query(value = "SELECT * FROM news ORDER BY \"date\" DESC LIMIT :limit", nativeQuery = true)
    List<News> findLatestNews(@Param("limit") Long limit);
}
