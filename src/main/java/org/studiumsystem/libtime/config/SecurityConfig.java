package org.studiumsystem.libtime.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.studiumsystem.libtime.model.LibUser;
import org.studiumsystem.libtime.model.SecurityUser;
import org.studiumsystem.libtime.repository.UserRepository;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            LibUser user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
            return new SecurityUser(user);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(
                c->c.disable()
        );

        http.authorizeHttpRequests(
                c -> c.requestMatchers("/libtime", "/register").permitAll()
                        //permit access to css file
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
        );


        http.formLogin(
                f -> f.loginPage("/libtime")
                        .defaultSuccessUrl("/main",true)
                        .permitAll()//here pert also post
        );

        return http.build();
    }
}
