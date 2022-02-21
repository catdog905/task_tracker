package ru.innopolis.university.task_tracker.DTO;

import lombok.*;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.Task;
import ru.innopolis.university.task_tracker.models.TaskStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TaskDTO {
    Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private int priority;
}
