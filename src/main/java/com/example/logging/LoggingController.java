package com.example.logging;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class LoggingController {
    @Autowired
    LoggingService service;


    @GetMapping("/")
    String loadLogin() {
        return "login";
    }

    @PostMapping("/")
    public RedirectView postLogin(Model model, HttpSession session, @RequestParam String username,
                                  @RequestParam String password, RedirectAttributes ra) {
        RedirectView rvHome = new RedirectView("/home3", true);
        RedirectView rvLogin = new RedirectView("/", true);
        for (var user : service.getUsers()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                session.setAttribute("userTimeRegistrations", service.getUserTimeRegistrations(user.getId()));
                return rvHome;
            }
        }
        ra.addFlashAttribute("messageLoginFailed", "Login failed, please try again.");
        return rvLogin;

    }

    @GetMapping("/signup")
    public String loadSignup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView submitSignupPost(@Valid User user, BindingResult bindingResult, @RequestParam String repeatPassword, RedirectAttributes ra) {
        return service.signupValidation(user, bindingResult, repeatPassword, ra);
    }

    @GetMapping("/home")
    public String loadTimReg(@RequestParam(required = false) String id, Model model, HttpSession session, TimeRegistration timeRegistration) {
        User user = (User) session.getAttribute("user");
        service.modelGeneration(model,id != null ? service.getTimeRegistrationById(Integer.valueOf(id)) : new TimeRegistration());
        session.setAttribute("userTimeRegistrations", service.getUserTimeRegistrations(user.getId()));
        return "home";
    }
    @GetMapping("/home3")
    public String paginationTEST(@RequestParam(required = false) String id, Model model, HttpSession session) {
        return getPaginatedTimeReg(id,model,session,1, "date", "asc");
    }
    @GetMapping("/page/{currentPage}")
    public String getPaginatedTimeReg(@RequestParam(required = false) String id, Model model, HttpSession session,
                                      @PathVariable(required = false) int currentPage,
                                      @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        User user = (User) session.getAttribute("user");
        //sortDir = "asc";

        service.paginationModelGeneration(model,id != null ? service.getTimeRegistrationById(Integer.valueOf(id)) : new TimeRegistration(),
                session, currentPage, sortField, sortDir);

        return "home";
    }

    @PostMapping("/home")
    public RedirectView PostTimeReg(HttpSession session, Model model, @Valid TimeRegistration timeRegistration,
                              BindingResult br, RedirectAttributes ra) {
        RedirectView rvHome = new RedirectView("/home", true);

        User user = (User) session.getAttribute("user");
        service.homeValidation(timeRegistration, br);

        if (br.hasErrors()) {
            System.out.println("Br has errors");
            service.modelGeneration(model,new TimeRegistration());
            return rvHome;
        }
        timeRegistration.setUserId(user.getId());
        service.saveTime(timeRegistration);

        ra.addFlashAttribute("SuccesTimeReg", "We have received your input. An overview can be retrieved below");
        return rvHome;
    }
    
    //Edit & Delete funktions
    @DeleteMapping("/home/{id}") //or /delete/id?
    public void delete(@PathVariable("id") Integer id){
        TimeRegistration timeRegistration = service.deleteTime(id);}
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
