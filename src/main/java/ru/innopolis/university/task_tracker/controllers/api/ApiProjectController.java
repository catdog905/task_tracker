package ru.innopolis.university.task_tracker.controllers.api;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.university.task_tracker.DTO.ProjectDTO;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;
import ru.innopolis.university.task_tracker.repositories.TasksRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ApiProjectController {
    private final ProjectsRepository projectsRepository;
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;

    public ApiProjectController(ProjectsRepository projectsRepository, TasksRepository tasksRepository, ModelMapper modelMapper) {
        this.projectsRepository = projectsRepository;
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProjectDTO> getProjects() {
        return projectsRepository.findAll().stream().map(
                project -> modelMapper.map(project, ProjectDTO.class)
        ).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> addProject(@RequestBody ProjectDTO projectDTO) {
        if (Objects.nonNull(projectDTO.getId()))
            return ResponseEntity.badRequest().body("Project id can not be defined while creating");
        if (Objects.isNull(projectDTO.getName()))
            return ResponseEntity.badRequest().body("Project name is missed");
        if (Objects.nonNull(projectDTO.getTaskSet()))
            for(Long taskId : projectDTO.getTaskSet())
                if (!tasksRepository.existsById(taskId))
                    return ResponseEntity.badRequest().body("Task " + taskId + " does not exist");
        Project savedProject = projectsRepository.save(modelMapper.map(projectDTO, Project.class));
        return ResponseEntity.ok(savedProject.getId().toString());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") Long id, @RequestBody ProjectDTO projectDTO) {
        if (!projectsRepository.existsById(id))
            return ResponseEntity.notFound().build();
        if (Objects.nonNull(projectDTO.getId()) && !Objects.equals(projectDTO.getId(), id))
            return ResponseEntity.badRequest().body("Project id can not be changed");
        if (Objects.isNull(projectDTO.getName()))
            return ResponseEntity.badRequest().body("Project name is missed");
        if (Objects.nonNull(projectDTO.getTaskSet()))
            for(Long taskId : projectDTO.getTaskSet())
                if (!tasksRepository.existsById(taskId))
                    return ResponseEntity.badRequest().body("Task " + taskId + " does not exist");
        Project savedProject = projectsRepository.save(modelMapper.map(projectDTO, Project.class));
        return ResponseEntity.ok(modelMapper.map(savedProject, ProjectDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProject(@PathVariable("id") Long id) {
        if (!projectsRepository.existsById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(projectsRepository.getById(id), ProjectDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Long id) {
        if (!projectsRepository.existsById(id))
            return ResponseEntity.notFound().build();
        projectsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
