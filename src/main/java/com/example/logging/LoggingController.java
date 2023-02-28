package com.example.logging;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LoggingController {
    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);

    @GetMapping("/login")
    String login(){
        return "login";
    }
    @PostMapping("/login")
    String postLogin() {
        return "login";
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("TypeRegTime", TypeRegTime.values());
        model.addAttribute("minDate",minDate.toString());
        model.addAttribute("maxDate",maxDate.toString());
        return "logging";
    }

    @PostMapping("/")
    public String registration(HttpSession session, @ModelAttribute User user, Model model) {
        List<User> users;

        if (session.getAttribute("users") == null) {
            users = new ArrayList<>();
        } else {
            users = (List) session.getAttribute("users");
        }

        users.add(user);

        session.setAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("TypeRegTime", TypeRegTime.values());
        model.addAttribute("minDate",minDate.toString());
        model.addAttribute("maxDate",maxDate.toString());

        System.out.println(minDate.toString());
        System.out.println(maxDate.toString());

        return "logging";
    }

}
