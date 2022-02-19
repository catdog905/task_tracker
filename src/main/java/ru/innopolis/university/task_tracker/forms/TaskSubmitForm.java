package ru.innopolis.university.task_tracker.forms;

import lombok.Data;
import ru.innopolis.university.task_tracker.models.TaskStatus;

@Data
public class TaskSubmitForm{
    private String name;
    private String description;
    private TaskStatus status;
    private int priority;
}
