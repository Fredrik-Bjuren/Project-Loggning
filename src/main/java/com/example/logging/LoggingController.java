package com.example.logging;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class LoggingController {

    @Autowired
    User user;

    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);

    @GetMapping("/")
    String login() {
        return "login";
    }

    @PostMapping("/")
    String postLogin(Model model) {
        model.addAttribute("userTimeRegistration", new TimeRegistration());
        model.addAttribute("TypeRegTime", TypeRegTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return "home";
    }


    @GetMapping("/home")
    public String home(@RequestParam(required = false) String sort, Model model) {
        if(sort !=null) {
            switch (sort) {
                case "category" -> user.sortByCategory();
                case "time" -> user.sortByHours();
                default -> user.sortByDate();
            }
        }
        model.addAttribute("userTimeRegistration", new TimeRegistration());
        model.addAttribute("TypeRegTime", TypeRegTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return "home";
    }

    @PostMapping("/home")
    public String registration(HttpSession session, Model model, @ModelAttribute TimeRegistration timeRegistration) {
        user.addTimeRegistration(timeRegistration);
        model.addAttribute("userTimeRegistration", new TimeRegistration());
        model.addAttribute("TypeRegTime", TypeRegTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return "home";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@RequestParam String username, @RequestParam String email,@RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,Model model) {
        user.setUserTimeRegistrations(new ArrayList<>());
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return "login";
    }


}
