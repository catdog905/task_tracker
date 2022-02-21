package ru.innopolis.university.task_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.university.task_tracker.DTO.ProjectDTO;
import ru.innopolis.university.task_tracker.forms.HowSort;
import ru.innopolis.university.task_tracker.forms.ProjectSortBy;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;
import ru.innopolis.university.task_tracker.forms.TasksSortBy;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.Task;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectServiceImpl(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    // REV: ???
    @Override
    public void createProject() {
        projectsRepository.save(new Project());
    }

    // REV: ?
    @Override
    public void deleteProject(Long projectId) {
        projectsRepository.deleteById(projectId);
    }

    @Override
    public void submitData(Long projectId, ProjectSubmitForm projectSubmitForm) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm");
        projectsRepository.save(Project.builder()
                .id(projectId)
                .name(projectSubmitForm.getName())
                .startDate(format.parse(projectSubmitForm.getStartDate()))
                .completionDate(format.parse(projectSubmitForm.getCompletionDate()))
                .status(projectSubmitForm.getStatus())
                //.taskSet(projectsRepository.getById(projectId).getTaskSet())
                .priority(projectSubmitForm.getPriority())
                .build());
    }

    @Override
    public ProjectDTO getProjectDTOWithSortedTaskList(ProjectDTO projectDTO, TasksSortBy tasksSortBy, HowSort howSort) {
        return null;
    }

    /*
    @Override
    public ProjectDTO getProjectDTOWithSortedTaskList(ProjectDTO projectDTO, TasksSortBy tasksSortBy, HowSort howSort) {
        Comparator<Task> comparator = Comparator.comparing(Task::getName);
        switch (tasksSortBy){
            case NAME:
                comparator = Comparator.comparing(Task::getName);
                break;
            case STATUS:
                comparator = Comparator.comparing(Task::getStatus);
                break;
            case PRIORITY:
                comparator = Comparator.comparing(Task::getPriority);
        }
        List<Task> taskList = projectDTO.getTaskSet().stream().sorted(comparator).collect(Collectors.toList());
        ProjectDTO newProjectDTO = ProjectDTO.builder()
                .name(projectDTO.getName())
                .startDate(projectDTO.getStartDate())
                .completionDate(projectDTO.getCompletionDate())
                .priority(projectDTO.getPriority())
                .status(projectDTO.getStatus())
                .taskSet(projectDTO.getTaskSet())
                .build();
        if (howSort == HowSort.DESCENDING)
            Collections.reverse(taskList);
        newProjectDTO.setTaskSet(taskList);
        return newProjectDTO;
    }*/

    @Override
    public List<ProjectDTO> sortProjectDTOListAccordingConditions(List<ProjectDTO> projectsDTO, ProjectSortBy projectSortBy, HowSort howSort) {
        Comparator<ProjectDTO> comparator = Comparator.comparing(ProjectDTO::getName);
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm");
        switch (projectSortBy){
            case NAME:
                comparator = Comparator.comparing(ProjectDTO::getName);
                break;
            case STATUS:
                comparator = Comparator.comparing(ProjectDTO::getStatus);
                break;
            case PRIORITY:
                comparator = Comparator.comparing(ProjectDTO::getPriority);
                break;
            case START_DATE:
                comparator = Comparator.comparing(x -> format.format(x.getStartDate()));
                break;
            case COMPLETION_DATE:
                comparator = Comparator.comparing(x -> format.format(x.getCompletionDate()));

        }
        List<ProjectDTO> projectsList = projectsDTO.stream().sorted(comparator).collect(Collectors.toList());
        if (howSort == HowSort.DESCENDING)
            Collections.reverse(projectsList);
        return projectsList;
    }
}
