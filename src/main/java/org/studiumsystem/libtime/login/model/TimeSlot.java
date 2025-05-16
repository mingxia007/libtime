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
    private long id;
    private LocalDate localDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String duration;
    private long userId;

    public long getUserId() {
        return userId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public long getId() {
        return id;
    }

    public String getDuration() {
        return duration;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "localDate=" + localDate +
                ", startOfLearningDay=" + startTime +
                ", endOfLearningDay=" + endTime +
                ", durationOfLearningDay=" + duration +
                '}';
    }
}
