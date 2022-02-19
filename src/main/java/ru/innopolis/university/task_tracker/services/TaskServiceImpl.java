package ru.innopolis.university.task_tracker.services;

import org.springframework.stereotype.Service;
import ru.innopolis.university.task_tracker.forms.TaskSubmitForm;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.Task;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;
import ru.innopolis.university.task_tracker.repositories.TasksRepository;

@Service
public class TaskServiceImpl implements TaskService {
    private final TasksRepository tasksRepository;
    private final ProjectsRepository projectsRepository;

    public TaskServiceImpl(TasksRepository tasksRepository, ProjectsRepository projectsRepository) {
        this.tasksRepository = tasksRepository;
        this.projectsRepository = projectsRepository;
    }

    @Override
    public void submitData(Long taskId, TaskSubmitForm taskSubmitForm) {
        tasksRepository.save(Task.builder()
                .id(taskId)
                .name(taskSubmitForm.getName())
                .description(taskSubmitForm.getDescription())
                .status(taskSubmitForm.getStatus())
                .priority(taskSubmitForm.getPriority())
                .project(tasksRepository.getById(taskId).getProject())
                .build());
    }

    @Override
    public void deleteTask(Long taskId, Long projectId) {
        tasksRepository.deleteById(taskId);
    }

    @Override
    public void createTask(Long projectId) {
        Task task = new Task();
        tasksRepository.save(task);
        Project project = projectsRepository.findById(projectId).orElse(new Project());
        task.setProject(project);
        project.getTaskSet().add(task);
        projectsRepository.save(project);
    }
}
