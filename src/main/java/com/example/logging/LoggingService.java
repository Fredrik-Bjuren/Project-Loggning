package com.example.logging;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class LoggingService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeRegistrationRepository trRepository;
    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);


    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }


    public List<TimeRegistration> getUserTimeRegistrations(Integer userId) {
        return (List<TimeRegistration>) trRepository.findByUserId(userId);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public TimeRegistration saveTime(TimeRegistration timeRegistration) {
        return trRepository.save(timeRegistration);
    }

    public RedirectView signupValidation(User user, BindingResult bindingResult, String repeatPassword, RedirectAttributes ra) {
        RedirectView rvSignUp = new RedirectView("/signup", false);
        RedirectView rvLogin = new RedirectView("/", true);
        if (!repeatPassword.equals(user.getPassword())) {
            bindingResult.rejectValue("password", "error", "Not the same password.");
            ra.addFlashAttribute("FailedSignup", "Something went wrong, please try again.");
            return rvSignUp;
        }
        if (bindingResult.hasErrors()) {;
            ra.addFlashAttribute("FailedSignup", "Something went wrong, please try again.");
            return rvSignUp;
        }

        addUser(user);
        ra.addFlashAttribute("SuccesSignup", "Your registration is confirmed.");
        return rvLogin;
    }



    public void homeValidation(TimeRegistration tr, BindingResult bindingResult) {

        if (tr.getTime() == 0) {
            System.out.println("Time = null");
            bindingResult.rejectValue("time", "error", "Please enter time.");
        }
        if (tr.getDate() == null) {
            System.out.println("Date is empty");
            bindingResult.rejectValue("date", "error", "Please enter date.");
        }
    }

    public Model modelGeneration(Model model) {
        model.addAttribute("timeRegistration", new TimeRegistration());
        model.addAttribute("TypeOfTime", TypeOfTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return model;
    }

     public List<TimeRegistration> createTestArray() {
        List<TimeRegistration> testArray = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            testArray.add(new TimeRegistration(random.nextInt(7) + 1, LocalDate.now().plusDays(random.nextInt(30) + 1),
                    random.nextDouble(7) + 1, "Paid Leave", "Comment",
                    LocalDateTime.now().plusDays(random.nextInt(30) + 1).plusHours(
                            (long) (random.nextDouble(3.67) + 1)),
                    LocalDateTime.now().plusDays(random.nextInt(30) + 1).plusHours(
                            (long) (random.nextDouble(4.43) + 1))));
        }
        String listString = testArray.stream().map(Object::toString).collect(Collectors.joining(", "));
        System.out.println("This is listString: " + listString);
        return testArray;
    }

}
