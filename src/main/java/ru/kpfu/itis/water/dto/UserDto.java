package ru.kpfu.itis.water.dto;

import lombok.*;
import ru.kpfu.itis.water.model.User;

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
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private UserDto(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
    }

    public static UserDto createOnTicketMessage(User user) {
        return new UserDto(user);
    }
}
