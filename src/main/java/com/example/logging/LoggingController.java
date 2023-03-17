package com.example.logging;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class LoggingController {
    @Autowired
    LoggingService service;



    @GetMapping("/")
    String loadLogin() {
        /*model.addAttribute("users", service.getUsers());*/
        //service.createTestArray(); <- generate sqldata
        return "login";
    }

    @PostMapping("/")
    public RedirectView postLogin(Model model, HttpSession session, @RequestParam String username, @RequestParam String password, RedirectAttributes ra) {
        RedirectView rvHome = new RedirectView("/home", true);
        RedirectView rvLogin = new RedirectView("/", true);
        for (var user : service.getUsers()) {
           if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                session.setAttribute("userTimeRegistrations",service.getUserTimeRegistrations(user.getId()));
//               model.addAttribute("userTimeRegistration", new TimeRegistration()); //Do we need this here??
               return rvHome;
            }
        }
        ra.addFlashAttribute("messageLoginFailed", "Login failed, please try again.");
        return rvLogin;

    }


    @GetMapping("/home")
    public String home(Model model,
                       HttpSession session, TimeRegistration timeRegistration) {
        User user = (User) session.getAttribute("user");

        service.modelGeneration((model));
        session.setAttribute("userTimeRegistrations",service.getUserTimeRegistrations(user.getId()));
        return "home";
    }

  @PostMapping("/home")
    public String registration(HttpSession session, Model model, @Valid TimeRegistration timeRegistration,
                               BindingResult br) {

        User user = (User) session.getAttribute("user");
        service.homeValidation(timeRegistration,br);

        if(br.hasErrors()) {
            System.out.println("Br has errors");
            service.modelGeneration(model);
           return "home";
        }

        timeRegistration.setUserId(user.getId());
        service.saveTime(timeRegistration);
        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String loadSignup(Model model) {
        model.addAttribute("user",new User());
        return "signup";
    }

   @PostMapping("/signup")
    public String submitSignupPost(@Valid User user, BindingResult bindingResult, @RequestParam String repeatPassword) {

        service.addUser(user);

        return service.signupValidation(user,bindingResult,repeatPassword);
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
