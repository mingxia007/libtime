package org.studiumsystem.libtime.login.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import org.studiumsystem.libtime.login.model.LibUser;

import java.util.Optional;


public interface UserRepository extends CrudRepository<LibUser, Long>{

    @Query("SELECT * FROM users WHERE username = :username")
    Optional<LibUser> findByUsername(String username);

    boolean existsByUsername(String username);
}

