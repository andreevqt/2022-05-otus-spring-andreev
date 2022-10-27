package ru.otus.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping(path = "")
  public String index() {
    return "redirect:/books";
  }

  @GetMapping(path = "/login")
  public String login(Model model) {
    model.addAttribute("title", "Sign in");
    model.addAttribute("loginError", false);
    return "login";
  }

  @GetMapping(path = "/login-error")
  public String loginError(Model model) {
    model.addAttribute("title", "Sign in");
    model.addAttribute("loginError", true);
    return "login";
  }

}
