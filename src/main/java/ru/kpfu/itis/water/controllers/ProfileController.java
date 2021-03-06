package ru.kpfu.itis.water.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.water.model.UserData;
import ru.kpfu.itis.water.security.roles.UserRole;
import ru.kpfu.itis.water.util.AuthenticationUtil;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/user")
public class ProfileController {

    private AuthenticationUtil authenticationUtil;

    public ProfileController(AuthenticationUtil authenticationUtil) {
        this.authenticationUtil = authenticationUtil;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(@ModelAttribute("model")ModelMap model, Authentication authentication) {
        UserData userData = authenticationUtil.getUserDataByAuthentication(authentication);
        model.addAttribute("user", userData.getUser());
        model.addAttribute("isNotUser", !userData.getUserRole().equals(UserRole.USER));
        return "profile-page";
    }
}
