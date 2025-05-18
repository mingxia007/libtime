package org.studiumsystem.libtime.login.service;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.studiumsystem.libtime.login.common.NotCheckInException;
import org.studiumsystem.libtime.login.model.LibUser;
import org.studiumsystem.libtime.login.model.TimeSlot;
import org.studiumsystem.libtime.login.repository.TimeSlotRepository;
import org.studiumsystem.libtime.login.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class UserService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserService.class);
    //how to add in database?
    private final UserRepository userRepository;
    private  final TimeSlotRepository timeSlotRepository;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository,
                       TimeSlotRepository timeSlotRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String password){
        String passwordEncodeded = passwordEncoder.encode(password);
        LibUser newLibUser = new LibUser(username, passwordEncodeded);
        userRepository.save(newLibUser);
    }

    public boolean existUser(String username){
        return userRepository.existsByUsername(username);
    }


    public  void checkIn(){
        LibUser libUser = getUserSuccessAuth();
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setLocalDate(LocalDate.now());
        timeSlot.setStartTime(LocalTime.now());
        timeSlot.setUserId(libUser.getId());
        timeSlotRepository.save(timeSlot);
    }

    //get LibUser after successful authentication in log in
    public LibUser getUserSuccessAuth(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    //record going time
    //return learning duration
    //if not check in throw Exception n
    public  String chekOut(){
        LibUser libUser = getUserSuccessAuth();
        TimeSlot timeSlot = timeSlotRepository
                .findTimeSlotByUserAndDate(libUser.getId(), LocalDate.now())
                .orElseThrow(NotCheckInException::new);
        LocalTime start = timeSlot.getStartTime();
        LocalTime end = LocalTime.now();
        timeSlot.setEndTime(end);
        Duration libDuration = Duration.between(start, end);
        timeSlot.setDuration(libDuration.toString());
        timeSlotRepository.save(timeSlot);
        //duration in database in form "8h6m4s"
        return libDuration.toString().replace("PT", "").toLowerCase();
    }

    //get the timeslots of the current authenticated user in the current week
    public List<TimeSlot> getTimeSlots(){
        LibUser libUser = getUserSuccessAuth();

        var l =  timeSlotRepository.findTimeSlotsCurrentWeekByUser(libUser.getId());
        logger.info("List length: " + l.size());
        return l;
    }

}
