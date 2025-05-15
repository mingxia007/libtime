package org.studiumsystem.libtime.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Table("timeslots")
public class TimeSlot {
    @Id
    private long id;
    private LocalDate localDate;
    private LocalTime startOfLearningDay;
    private LocalTime endOfLearningDay;
    private Duration durationOfLearningDay;
    private User user;

    public User getUser() {
        return user;
    }
    public LocalDate getLocalDate() {
        return localDate;
    }

    public long getId() {
        return id;
    }

    public Duration getDurationOfLearningDay() {
        return durationOfLearningDay;
    }

    public LocalTime getEndOfLearningDay() {
        return endOfLearningDay;
    }

    public LocalTime getStartOfLearningDay() {
        return startOfLearningDay;
    }

    public void setEndOfLearningDay(LocalTime endOfLearningDay) {
        this.endOfLearningDay = endOfLearningDay;
    }

    public void setStartOfLearningDay(LocalTime startOfLearningDay) {
        this.startOfLearningDay = startOfLearningDay;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDurationOfLearningDay(Duration durationOfLearningDay) {
        this.durationOfLearningDay = durationOfLearningDay;
    }
}
