package org.studiumsystem.libtime.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table("users")
public class User {

    @Id
    @Column("user_id")
    private Long id;//auto generate by databank
    @Column("username")
    private String username;
    @Column("password")
    private String password;

    @MappedCollection(idColumn = "user_id")
    private List<TimeSlot> timeSlots;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        timeSlots = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
