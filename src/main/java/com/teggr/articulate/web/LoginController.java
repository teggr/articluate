package com.teggr.articulate.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", defaultValue = "false") boolean error,
                        @RequestParam(name = "logout", defaultValue = "false") boolean logout,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "loginView";
    }
}
