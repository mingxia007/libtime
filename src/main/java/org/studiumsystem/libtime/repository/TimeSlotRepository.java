package org.studiumsystem.libtime.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.studiumsystem.libtime.model.TimeSlot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {

    //find the timeslot record of a specific user at a specific day
    @Query("SELECT * FROM timeslots WHERE  user_id = :userid")
    Optional<TimeSlot> findTimeSlotByUserAndDate(long userid, LocalDate localDate);

    //find the timeslots of the current week of a specific user
    //how to find only onece
    @Query("SELECT * FROM timeslots " +
               "WHERE user_id = :userid " +
               "AND local_date >= date_trunc('week', current_date) " +
               "AND local_Date <= current_date")
    List<TimeSlot> findTimeSlotsCurrentWeekByUser(long userid);
}
