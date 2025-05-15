package org.studiumsystem.libtime.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name="timeslots")
public class TimeSlot {

    @Id
    @Column("timeslot_id")
    private long id;
    @Column("local_date")
    private LocalDate localDate;
    @Column("start_time")
    private LocalTime startOfLearningDay;
    @Column("end_time")
    private LocalTime endOfLearningDay;

    @Column("duration")
    private String durationOfLearningDay;

    @Column("user_id")
    private long user_id;

    public long getUser_id() {
        return user_id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public long getId() {
        return id;
    }

    public String getDurationOfLearningDay() {
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

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDurationOfLearningDay(String durationOfLearningDay) {
        this.durationOfLearningDay = durationOfLearningDay;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "localDate=" + localDate +
                ", startOfLearningDay=" + startOfLearningDay +
                ", endOfLearningDay=" + endOfLearningDay +
                ", durationOfLearningDay=" + durationOfLearningDay +
                '}';
    }
}
