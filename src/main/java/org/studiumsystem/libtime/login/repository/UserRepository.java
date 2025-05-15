package org.studiumsystem.libtime.login.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.studiumsystem.libtime.login.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{
}
