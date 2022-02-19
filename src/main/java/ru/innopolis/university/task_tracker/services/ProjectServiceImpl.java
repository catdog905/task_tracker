package ru.innopolis.university.task_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectServiceImpl(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }


    @Override
    public void createProject() {
        projectsRepository.save(new Project());
    }

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
                .taskSet(projectsRepository.getById(projectId).getTaskSet())
                .priority(projectSubmitForm.getPriority())
                .build());
    }
}
