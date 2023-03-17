package com.example.logging;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class LoggingService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeRegistrationRepository trRepository;
    LocalDate minDate = LocalDate.now().minusDays(30);
    LocalDate maxDate = LocalDate.now().plusDays(365);


    public List<User> getUsers(){
    return (List<User>) userRepository.findAll();
    }


    public List<TimeRegistration> getUserTimeRegistrations(Integer userId) {
       return  (List<TimeRegistration>) trRepository.findByUserId(userId);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public TimeRegistration saveTime(TimeRegistration timeRegistration) {
        return trRepository.save(timeRegistration);
    }

    public String signupValidation(User user, BindingResult bindingResult, String repeatPassword){

        if (!repeatPassword.equals(user.getPassword())) {
            bindingResult.rejectValue("password", "error","Not the same password.");
            return "signup";
        }
        if(bindingResult.hasErrors()){;
            return "signup";
        }
        addUser(user);
        return "login";
    }
    public String homeValidation(TimeRegistration tr, BindingResult bindingResult) {

        if(tr.getTime() == 0) {
            System.out.println("Time = null");
            bindingResult.rejectValue("time","error","Please enter time.");
            return "Invalid time";
        }
        if(tr.getDate() == null) {
            System.out.println("Date is empty");
            bindingResult.rejectValue("date","error","Please enter date.");
            return "Empty date";
        }
        return "Passed homeValidation";
    }
    public Model modelGeneration(Model model) {
        model.addAttribute("timeRegistration", new TimeRegistration());
        model.addAttribute("TypeOfTime", TypeOfTime.values());
        model.addAttribute("minDate", minDate.toString());
        model.addAttribute("maxDate", maxDate.toString());
        return model;
    }

  


/*    public void setUserTimeRegistrations(List<TimeRegistration> userTimeRegistrations) {
        this.userTimeRegistrations = userTimeRegistrations;
    }

    public double getTotalTime(){
        return userTimeRegistrations.stream().mapToDouble(x->x.getTime()).sum();
    }
    public void addTimeRegistration(TimeRegistration timeRegistration){
        userTimeRegistrations.add(timeRegistration);
    }*/
    /*    public double getEnumSum(TypeRegTime type){
        return userTimeRegistrations.stream().filter(z->z.getEnumType()==type).mapToDouble(z->z.getTime()).sum();
    }*/
//    public void sortByDate() {
//        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getDate));
//        System.out.println("Date");
//    }
//
//    public void sortByHours() {
//        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getTime));
//        System.out.println("Hours");
//    }
//
//    public void sortByCategory() {
//        userTimeRegistrations.sort(Comparator.comparing(TimeRegistration::getEnumType));
//        System.out.println("Category");
//    }

public List<TimeRegistration> createTestArray() {
        List<TimeRegistration> testArray = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            testArray.add(new TimeRegistration(
                    random.nextInt(7)+1,
                    LocalDate.now().plusDays(random.nextInt(30)+1),
                    random.nextDouble(7)+1,
                    "Paid Leave",
                    "Comment",
                    LocalDateTime.now().plusDays(random.nextInt(30)+1).plusHours((long) (random.nextDouble(3.67)+1)),
                    LocalDateTime.now().plusDays(random.nextInt(30)+1).plusHours((long) (random.nextDouble(4.43)+1))));
        }
    String listString = testArray.stream().map(Object::toString)
            .collect(Collectors.joining(", "));
    System.out.println("This is listString: " + listString);
        return testArray;
    }

}
