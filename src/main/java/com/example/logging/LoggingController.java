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

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class LoggingController {

    UserRepository userRepository = new UserRepository();
    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);

    @GetMapping("/")
    String login(Model model) {
        model.addAttribute("users",userRepository.getUsers());
        return "login";
    }

    @PostMapping("/")
    String postLogin(Model model, HttpSession session, @RequestParam String username, @RequestParam String password) {
        for (var user : userRepository.getUsers()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                session.setAttribute("user", user);
            }
//            else {
//                return "login";
//            }
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
//        for (var timereg:user.getUserTimeRegistrations()) {
//            if(timereg.getId()==id){
//                model.addAttribute("userTimeRegistration", timereg);
//                List <TimeRegistration> newList = user.getUserTimeRegistrations();
//                newList.remove(timereg);
//            }
//            else{
//                model.addAttribute("userTimeRegistration", new TimeRegistration());
//            }
        model.addAttribute("workSum", (int) (user.getEnumSum(TypeRegTime.WORK)));
        model.addAttribute("paidLeaveSum",(int) (user.getEnumSum(TypeRegTime.PAID_LEAVE)));
        model.addAttribute("unpaidLeaveSum",(int) (user.getEnumSum(TypeRegTime.UNPAID_LEAVE)));
        model.addAttribute("sickLeaveSum",(int) (user.getEnumSum(TypeRegTime.SICK_LEAVE)));
        model.addAttribute("overtimeSum",(int) (user.getEnumSum(TypeRegTime.OVERTIME)));
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
    public String signup(HttpSession session, Model model, User user) {
        model.addAttribute("user",user);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(Model model,HttpSession session,
                             @Valid User user, BindingResult bindingResult) {

        model.addAttribute("user",user);
        String valid = validation(user,bindingResult);
        userRepository.addUser(user);

        return valid;
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
    public String validation(User user, BindingResult bindingResult){

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            bindingResult.rejectValue("password", "error","Not the same password.");
            return "signup";
        }
        if(bindingResult.hasErrors()){;
            return "signup";
        }
        return "login";
    }
}
