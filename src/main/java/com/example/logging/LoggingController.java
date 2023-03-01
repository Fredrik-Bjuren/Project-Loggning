package com.example.logging;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
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

    UserRepository userRepository = new UserRepository();
    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);

    @GetMapping("/")
    String login() {
        return "login";
    }

    @PostMapping("/")
    String postLogin(Model model, HttpSession session, @RequestParam String username, @RequestParam String password) {
        for (var user : userRepository.getUsers()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                session.setAttribute("user", user);
            }
        }

        model.addAttribute("userTimeRegistration", new TimeRegistration());
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home(@RequestParam(required = false) String sort, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (sort != null) {
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
        User user = (User) session.getAttribute("user");
        user.addTimeRegistration(timeRegistration);

        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@RequestParam String username, @RequestParam String email, @RequestParam String password,
                             @RequestParam String firstName, @RequestParam String lastName, Model model,
                             HttpSession session) {
        User user = new User(username, email, firstName, lastName, password);
        userRepository.addUser(user);
        System.out.println(userRepository);
        System.out.println(user);
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse res) {
        session.removeAttribute("username"); // this would be an ok solution
        session.invalidate(); // you could also invalidate the whole session, a new session will be created the next request
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "login";
    }
}
