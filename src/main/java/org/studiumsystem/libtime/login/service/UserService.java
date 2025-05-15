package org.studiumsystem.libtime.login.service;

import org.springframework.stereotype.Service;
import org.studiumsystem.libtime.login.model.TimeSlot;
import org.studiumsystem.libtime.login.model.User;
import org.studiumsystem.libtime.login.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    //how to add in database?
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean createUser(String username, String password){
        User newUser = new User(username, password);
        userRepository.save(newUser);
        return true;
    }

    public static void checkIn(User user){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setLocalDate(LocalDate.now());
        timeSlot.setStartOfLearningDay(LocalTime.now());
        user.getTimeSlots().add(timeSlot);
    }

    public static void chekOut(User user){

    }

}
