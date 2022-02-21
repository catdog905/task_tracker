package ru.innopolis.university.task_tracker.controllers.api;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.university.task_tracker.DTO.ProjectDTO;
import ru.innopolis.university.task_tracker.DTO.TaskDTO;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.Task;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;
import ru.innopolis.university.task_tracker.repositories.TasksRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class ApiTaskController {
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;

    public ApiTaskController(TasksRepository tasksRepository, ModelMapper modelMapper) {
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<TaskDTO> getTasks() {
        return tasksRepository.findAll().stream().map(
                task -> modelMapper.map(task, TaskDTO.class)
        ).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO) {
        if (Objects.nonNull(taskDTO.getId()))
            return ResponseEntity.badRequest().body("Project id can not be defined while creating");
        if (Objects.isNull(taskDTO.getName()))
            return ResponseEntity.badRequest().body("Project name is missed");
        Task task = tasksRepository.save(modelMapper.map(taskDTO, Task.class));
        return ResponseEntity.ok(task.getId().toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id){
        if (!tasksRepository.existsById(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(modelMapper.map(tasksRepository.getById(id), TaskDTO.class));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        if (!tasksRepository.existsById(id))
            return ResponseEntity.notFound().build();
        if (Objects.nonNull(taskDTO.getId()) && !Objects.equals(taskDTO.getId(), id))
            return ResponseEntity.badRequest().body("Project id can not be changed");
        if (Objects.isNull(taskDTO.getName()))
            return ResponseEntity.badRequest().body("Project name is missed");

        return ResponseEntity.ok(modelMapper.map(tasksRepository.save(modelMapper.map(taskDTO, Task.class)),
                TaskDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!tasksRepository.existsById(id))
            return ResponseEntity.notFound().build();
        tasksRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
