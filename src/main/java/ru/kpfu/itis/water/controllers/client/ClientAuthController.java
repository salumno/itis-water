package ru.kpfu.itis.water.controllers.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.water.form.client.AuthenticationResponse;
import ru.kpfu.itis.water.form.client.LoginPasswordForm;
import ru.kpfu.itis.water.util.AuthenticationUtil;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/client/login")
public class ClientAuthController {

    private AuthenticationUtil authenticationUtil;

    public ClientAuthController(AuthenticationUtil authenticationUtil) {
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginPasswordForm form) {
        if (authenticationUtil.isCredentialsValid(form)) {
            return ResponseEntity.ok(authenticationUtil.createAuthResponse(form));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
