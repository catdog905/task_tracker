package ru.innopolis.university.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.innopolis.university.task_tracker.models.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
