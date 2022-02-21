package ru.innopolis.university.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.university.task_tracker.models.Project;

public interface ProjectsRepository extends JpaRepository<Project, Long> {
}
