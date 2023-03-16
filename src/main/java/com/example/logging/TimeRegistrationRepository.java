package com.example.logging;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration,Integer> {
}
