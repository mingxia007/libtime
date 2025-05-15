package org.studiumsystem.libtime.login.service;

import org.springframework.stereotype.Service;
import org.studiumsystem.libtime.login.controller.MainController;
import org.studiumsystem.libtime.login.model.LibUser;
import org.studiumsystem.libtime.login.model.TimeSlot;
import org.studiumsystem.libtime.login.repository.TimeSlotRepository;
import org.studiumsystem.libtime.login.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;


@Service
public class UserService {
    //how to add in database?
    private final UserRepository userRepository;
    private  final TimeSlotRepository timeSlotRepository;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository, TimeSlotRepository timeSlotRepository){
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    public boolean createUser(String username, String password){
        LibUser newLibUser = new LibUser(username, password);
        userRepository.save(newLibUser);
        return true;
    }

    public LibUser getUserByUsername(String username){
        LibUser libUser;
        libUser =  userRepository.findByUsername(username).orElse(null);
        return libUser;
    }

    public  void checkIn(LibUser libUser){
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setLocalDate(LocalDate.now());
        timeSlot.setStartOfLearningDay(LocalTime.now());
        timeSlot.setUser_id(libUser.getId());
        timeSlotRepository.save(timeSlot);
    }

    //record going time
    //return learning duration
    public  String chekOut(LibUser libUser){
        TimeSlot timeSlot = timeSlotRepository
                .findTimeSlotByUserAndDate(libUser.getId(), LocalDate.now()).orElse(null);
        LocalTime start = timeSlot.getStartOfLearningDay();
        LocalTime end = LocalTime.now();
        timeSlot.setEndOfLearningDay(end);
        Duration libDuration = Duration.between(start, end);
        timeSlot.setDurationOfLearningDay(libDuration.toString());
        timeSlotRepository.save(timeSlot);
        return libDuration.toString().replace("PT", "").toLowerCase();
    }

}
