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

    private String role;

    private UserDto(User user) {
        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        role = user.getRole().toString();
    }

    public static UserDto from(User user) {
        return new UserDto(user);
    }
}
