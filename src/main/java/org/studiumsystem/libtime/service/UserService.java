package org.studiumsystem.libtime.service;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.studiumsystem.libtime.common.NotCheckInException;
import org.studiumsystem.libtime.model.LibUser;
import org.studiumsystem.libtime.model.Task;
import org.studiumsystem.libtime.model.TimeSlot;
import org.studiumsystem.libtime.repository.TaskRepository;
import org.studiumsystem.libtime.repository.TimeSlotRepository;
import org.studiumsystem.libtime.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;


@Service
public class UserService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserService.class);
    //how to add in database?
    private final UserRepository userRepository;
    private final TimeSlotRepository timeSlotRepository;
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
        timeSlot.setDuration("0s");
        timeSlotRepository.save(timeSlot);
    }

    //get LibUser that already successful authenticated
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
        //change duration to the string formal in database
        String date = TimeSlot.durationToString(libDuration);
        timeSlot.setDuration(date);
        timeSlotRepository.save(timeSlot);
        return TimeSlot.durationToString(libDuration);
    }

    //get the timeslots of the current authenticated user in the current week
    public List<TimeSlot> getTimeSlots(){
        LibUser libUser = getUserSuccessAuth();
        return  timeSlotRepository.findTimeSlotsCurrentWeekByUser(libUser.getId());
    }

    //calculate the sum of the duration of Timeslot lists, result in string form
    public String getSumDuration(List<TimeSlot> list){
        Duration durationOfSum = list.stream().map(TimeSlot::getDurationInstance)
                .reduce(Duration.ZERO, Duration::plus);
        return TimeSlot.durationToString(durationOfSum);
    }

}
