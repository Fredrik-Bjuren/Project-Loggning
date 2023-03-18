package com.example.logging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration,Integer> {

    List<TimeRegistration> findAllByUserId(Integer userId);

    List<TimeRegistration> findByUserId(Integer userId);


    void deleteById(Integer id);
}
