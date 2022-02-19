package ru.innopolis.university.task_tracker.forms;

import lombok.Data;
import ru.innopolis.university.task_tracker.models.ProjectStatus;

@Data
public class ProjectSubmitForm {
    private String name;
    private String startDate;
    private String completionDate;
    private ProjectStatus status;
    private int priority;
}
