package com.example.logging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface TimeRegistrationRepository extends CrudRepository<TimeRegistration,Integer>, PagingAndSortingRepository<TimeRegistration,Integer> {

    Optional<List<TimeRegistration>> findByUserId(Integer userId);

    //Page<TimeRegistration> findAllById(Integer userId, Pageable pageable);
}
