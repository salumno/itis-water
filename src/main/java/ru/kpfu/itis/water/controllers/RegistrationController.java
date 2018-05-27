package ru.kpfu.itis.water.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.water.form.RegistrationForm;
import ru.kpfu.itis.water.services.UserService;
import ru.kpfu.itis.water.validators.RegistrationFormValidator;

import javax.validation.Valid;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private RegistrationFormValidator registrationFormValidator;

    public RegistrationController(UserService userService, RegistrationFormValidator registrationFormValidator) {
        this.userService = userService;
        this.registrationFormValidator = registrationFormValidator;
    }

    @InitBinder("registrationForm")
    public void initFormValidator(WebDataBinder binder) {
        binder.addValidators(registrationFormValidator);
    }

    @GetMapping("")
    public String getRegistrationPage() {
        return "registration-page";
    }

    @PostMapping("")
    public String userRegistration(@Valid @ModelAttribute("registrationForm")RegistrationForm form,
                                   BindingResult errors,
                                   RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/registration";
        }
        userService.registerUser(form);
        return "successful-registration-page";
    }
}
