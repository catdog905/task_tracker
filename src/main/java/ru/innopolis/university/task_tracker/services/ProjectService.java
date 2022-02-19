package ru.innopolis.university.task_tracker.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;

import java.text.ParseException;

public interface ProjectService {
    void createProject();
    void deleteProject(Long projectId);
    void submitData(Long projectId, ProjectSubmitForm projectSubmitForm) throws ParseException;
}
