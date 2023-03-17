package com.example.logging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration,Integer> {

    Optional<List<TimeRegistration>> findByUserId(Integer userId);

}
