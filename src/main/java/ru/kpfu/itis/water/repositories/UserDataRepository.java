package ru.kpfu.itis.water.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.water.model.UserData;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface UserDataRepository extends JpaRepository<UserData, Long>{
    Optional<UserData> findOneByLogin(String login);
    Optional<UserData> findOneById(Long login);
}
