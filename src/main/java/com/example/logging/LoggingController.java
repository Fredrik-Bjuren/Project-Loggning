package com.example.logging;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoggingController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        model.addAttribute("user", new User("","",null, TypeRegTime.WORK));
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
        model.addAttribute("user", new User("","",null, TypeRegTime.WORK));

        model.addAttribute("TypeRegTime", TypeRegTime.values() );



        return "logging";
    }
}
