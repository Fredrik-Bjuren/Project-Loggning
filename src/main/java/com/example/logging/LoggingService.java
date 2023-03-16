package com.example.logging;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeRegistrationRepository trRepository;


    public List<User> getUsers(){
    return (List<User>) userRepository.findAll();
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

/*    public List<TimeRegistration> createTestArray() {
        List testArray = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            testArray.add(new TimeRegistration(random.nextDouble(7)+1,
                    TypeOfTime.values()[random.nextInt(5)],
                    LocalDate.now().plusDays(random.nextInt(30)+1).toString(),""));
        }
        return testArray;
    }*/
}
