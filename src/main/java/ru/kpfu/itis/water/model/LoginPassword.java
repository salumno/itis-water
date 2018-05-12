package ru.kpfu.itis.water.model;

import lombok.*;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginPassword {
    private String password;
    private String login;
}
