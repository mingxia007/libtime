package org.studiumsystem.libtime.login.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.studiumsystem.libtime.login.model.TimeSlot;

import java.time.LocalDate;
import java.util.Optional;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {

    @Query("SELECT * FROM timeslots WHERE  user_id = :user_id")
    Optional<TimeSlot> findTimeSlotByUserAndDate(long user_id, LocalDate localDate);

    @Query("SELECT * FROM timeslots WHERE  user_id = :user_id")
    Optional<TimeSlot> findTimeSlotByUser(long user_id);
}
