package com.example.logging;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration,Integer> {

    List<TimeRegistration> findAllByUserId(Integer userId);

    List<TimeRegistration> findByUserId(Integer userId);
}
