package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

  private PasswordEncoder passwordEncoder;

  @GetMapping(path = "")
  public String index() {
    return "redirect:/books";
  }

  @GetMapping(path = "/login")
  public String login(Model model) {
    model.addAttribute("title", "Sign in");
    model.addAttribute("loginError", false);
    System.out.println(passwordEncoder.matches("password", "$2a$10$wCOXwcZn3GSk.RCQNOGC/er.60y5mMItsuOJ0/CGuVkSHMVC6KSte"));
    System.out.println(passwordEncoder.encode("password"));
    return "login";
  }

  @GetMapping(path = "/login-error")
  public String loginError(Model model) {
    model.addAttribute("title", "Sign in");
    model.addAttribute("loginError", true);
    return "login";
  }

}
