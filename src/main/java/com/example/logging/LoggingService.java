package com.example.logging;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return (List<TimeRegistration>) trRepository.findByUserId(userId).orElse(new ArrayList<>());
    }
    public TimeRegistration getTimeRegistrationById(Integer id) {
        return (TimeRegistration) trRepository.findById(id).orElse(new TimeRegistration());
    }
    public Page<TimeRegistration> getUserTimeRegistrationsPagination(int pageNumber, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1,8, sort);
        return trRepository.findAll(pageable);
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

  public String homeValidation(TimeRegistration tr, BindingResult bindingResult) {
        if (tr.getTime() == 0) {
            System.out.println("Time = null");
            bindingResult.rejectValue("time","error","Please enter time.");
            return "Invalid time";
        }
        if (tr.getDate() == null) {
            System.out.println("Date is empty");
            bindingResult.rejectValue("date","error","Please enter date.");
            return "Empty date";
        }
        return "Passed homeValidation";
    }

    public Model modelGeneration(Model model, TimeRegistration timeRegistration) {
        model.addAttribute("timeRegistration", timeRegistration);
        model.addAttribute("TypeOfTime", TypeOfTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return model;
    }
    public Model paginationModelGeneration(Model model, TimeRegistration timeRegistration, HttpSession session, int currentPage, String sortField, String sortDir, User user) {
        Page<TimeRegistration> page = getUserTimeRegistrationsPagination(currentPage, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalRegistrations = page.getTotalElements();
        session.setAttribute("userTimeRegistrations", getUserTimeRegistrationsPagination(currentPage, sortField, sortDir)); //page
        model.addAttribute("timeRegistration", timeRegistration);
        model.addAttribute("TypeOfTime", TypeOfTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("totalRegistrations",totalRegistrations);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir",reverseSortDir);

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


    public TimeRegistration deleteTime(Integer id) {
        return new TimeRegistration();
    }
}
