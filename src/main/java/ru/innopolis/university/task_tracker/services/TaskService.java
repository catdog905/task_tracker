package ru.innopolis.university.task_tracker.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.innopolis.university.task_tracker.forms.TaskSubmitForm;

public interface TaskService {
    void submitData(Long taskId, TaskSubmitForm taskSubmitForm);
    void deleteTask(Long taskId, Long projectId);
    void createTask(Long projectId);
}
