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
    private Long id;
    private String name = "Task template";
    private String description = "";
    private TaskStatus status = TaskStatus.TODO;
    private int priority = 0;
    private Project project;

    public TaskDTO(Task task) {
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        status = task.getStatus();
        priority = task.getPriority();
        project = task.getProject();
    }
}
