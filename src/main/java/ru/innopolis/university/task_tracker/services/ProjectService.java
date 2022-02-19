package ru.innopolis.university.task_tracker.services;

import ru.innopolis.university.task_tracker.DTO.ProjectDTO;
import ru.innopolis.university.task_tracker.forms.HowSort;
import ru.innopolis.university.task_tracker.forms.ProjectSortBy;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;
import ru.innopolis.university.task_tracker.forms.TasksSortBy;

import java.text.ParseException;
import java.util.List;

public interface ProjectService {
    void createProject();
    void deleteProject(Long projectId);
    void submitData(Long projectId, ProjectSubmitForm projectSubmitForm) throws ParseException;
    ProjectDTO getProjectDTOWithSortedTaskList(ProjectDTO projectDTO, TasksSortBy tasksSortBy, HowSort howSort);
    List<ProjectDTO> sortProjectDTOListAccordingConditions(
            List<ProjectDTO> projectsDTO, ProjectSortBy projectSortBy, HowSort howSort);
}
