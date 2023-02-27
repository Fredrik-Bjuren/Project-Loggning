package com.example.logging;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loggingController {

    @GetMapping("/")
    public String home(){
        return "logging";
    }

    @PostMapping("/")
    public String registration(HttpSession session, User user){
        session.setAttribute("user", user);
        return "logging";
    }
}
