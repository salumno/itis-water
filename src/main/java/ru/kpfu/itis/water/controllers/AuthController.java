package ru.kpfu.itis.water.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.water.util.AuthenticationUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
public class AuthController {
    private AuthenticationUtil authenticationUtil;

    public AuthController(AuthenticationUtil authenticationUtil) {
        this.authenticationUtil = authenticationUtil;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("model")ModelMap model, Authentication authentication,
                        @RequestParam(name = "error", required = false) String error) {

        if (authentication != null) {
            return "redirect:/post-login";
        }
        model.addAttribute("error", error);
        return "login-page";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/post-login", method = RequestMethod.GET)
    public String postLogin(Authentication authentication) {
        return "redirect:" + authenticationUtil.defineDefaultURL(authentication);
    }
}
