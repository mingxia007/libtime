package org.studiumsystem.libtime.login.service;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import java.util.logging.Logger;


@Service
public class UserService {
    //how to add in database?
    private final UserRepository userRepository;
    private  final TimeSlotRepository timeSlotRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository,
                       TimeSlotRepository timeSlotRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public boolean createUser(String username, String password){
        String passwordEncodeded = passwordEncoder.encode(password);
        LibUser newLibUser = new LibUser(username, passwordEncodeded);
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
        timeSlot.setStartTime(LocalTime.now());
        timeSlot.setUserId(libUser.getId());
        timeSlotRepository.save(timeSlot);
    }

    public boolean authenUser(String username, String password){
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .isAuthenticated();
    }

    //record going time
    //return learning duration
    //if not check in throw Exception n
    public  String chekOut(LibUser libUser){
        TimeSlot timeSlot = timeSlotRepository
                .findTimeSlotByUserAndDate(libUser.getId(), LocalDate.now())
                .orElseThrow(NotCheckInException::new);
        LocalTime start = timeSlot.getStartTime();
        LocalTime end = LocalTime.now();
        timeSlot.setEndTime(end);
        Duration libDuration = Duration.between(start, end);
        timeSlot.setDuration(libDuration.toString());
        timeSlotRepository.save(timeSlot);
        return libDuration.toString().replace("PT", "").toLowerCase();
    }



}
