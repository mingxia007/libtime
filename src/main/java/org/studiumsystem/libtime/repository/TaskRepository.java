package org.studiumsystem.libtime.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.studiumsystem.libtime.model.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    //find all not finished task
    @Query("SELECT * FROM tasks WHERE user_id =:userId AND finished = false")
    List<Task> findAllByUserId(long userId);

    @Modifying
    @Query("UPDATE tasks SET finished = 'true' WHERE id =:id")
    void updateTaskFinishedById(long id);

    @Modifying
    @Query("UPDATE tasks SET content = :contentEdit WHERE id = :id")
    void updateTaskContentById(long id, String contentEidt);
}
