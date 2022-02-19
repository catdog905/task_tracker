package ru.innopolis.university.task_tracker.DTO;

import lombok.*;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.ProjectStatus;
import ru.innopolis.university.task_tracker.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProjectDTO {
    private Long id;
    private String name = "Template of the project";
    private String startDate = "";
    private String completionDate = "";
    private ProjectStatus status = ProjectStatus.NOT_STARTED;
    private int priority = 0;
    private List<Task> taskSet;

    public ProjectDTO(Project project) {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm");
        id = project.getId();
        name = project.getName();
        startDate = format.format(project.getStartDate());
        completionDate = format.format(project.getCompletionDate());
        status = project.getStatus();
        priority = project.getPriority();
        taskSet = project.getTaskSet();
    }
}
