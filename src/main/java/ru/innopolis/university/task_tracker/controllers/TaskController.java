package ru.innopolis.university.task_tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.university.task_tracker.DTO.TaskDTO;
import ru.innopolis.university.task_tracker.forms.TaskSubmitForm;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.Task;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;
import ru.innopolis.university.task_tracker.repositories.TasksRepository;
import ru.innopolis.university.task_tracker.services.TaskService;

@Controller
public class TaskController {
    private final TasksRepository tasksRepository;
    private final TaskService taskService;

    public TaskController(TasksRepository tasksRepository, TaskService taskService) {
        this.tasksRepository = tasksRepository;
        this.taskService = taskService;
    }


    @GetMapping("/task_list/{task_id}")
    public String getTaskPage(@PathVariable("task_id") Long taskId, ModelMap modelMap) {
        try {
            modelMap.addAttribute("task",
                    new TaskDTO(tasksRepository.findById(taskId)
                            .orElseThrow(IndexOutOfBoundsException::new)));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return "redirect:/error/no_such_task";
        }
        return "task_info";
    }

    @PostMapping("/task_list/{task_id}/submit_data")
    public String submitData(@PathVariable("task_id") Long taskId, TaskSubmitForm taskSubmitForm) {
        taskService.submitData(taskId, taskSubmitForm);
        return "redirect:/task_list/" + taskId;
    }

    @GetMapping("/projects_list/{project_id}/delete_task/{task_id}")
    public String deleteTask(@PathVariable("task_id") Long taskId, @PathVariable("project_id") Long projectId) {
        taskService.deleteTask(taskId, projectId);
        return "redirect:/projects_list/" + projectId;
    }

    @GetMapping("/projects_list/{project_id}/create_task")
    public String createTask(@PathVariable("project_id") Long projectId) {
        taskService.createTask(projectId);
        return "redirect:/projects_list/" + projectId;
    }
}
