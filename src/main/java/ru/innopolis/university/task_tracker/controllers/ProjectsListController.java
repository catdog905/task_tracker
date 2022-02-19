package ru.innopolis.university.task_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.university.task_tracker.DTO.ProjectDTO;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;
import ru.innopolis.university.task_tracker.services.ProjectService;

import java.text.ParseException;
import java.util.stream.Collectors;

@Controller
public class ProjectsListController {
    private final ProjectsRepository projectsRepository;
    private final ProjectService projectService;

    public ProjectsListController(ProjectsRepository projectsRepository, ProjectService projectService) {
        this.projectsRepository = projectsRepository;
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/projects_list";
    }

    @GetMapping("/projects_list")
    public String getProjectListPage(ModelMap modelMap) {
        modelMap.addAttribute("projects", projectsRepository.findAll().stream()
                .map(ProjectDTO::new).collect(Collectors.toList()));
        return "projects_list";
    }

    @GetMapping("/projects_list/{project_id}")
    public String getProjectPage(@PathVariable("project_id") Long projectId, ModelMap modelMap) {
        try {
            modelMap.addAttribute("project",
                    new ProjectDTO(projectsRepository.findById(projectId)
                            .orElseThrow(IndexOutOfBoundsException::new)));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return "redirect:/error/no_such_project";
        }
        return "project_info";
    }

    @PostMapping("/projects_list/{project_id}/submit_data")
    public String submitData(@PathVariable("project_id") Long projectId, ProjectSubmitForm projectSubmitForm) {
        try {
            projectService.submitData(projectId, projectSubmitForm);
        } catch (ParseException e) {
            return "redirect:/error/submit_data_error";
        }
        return "redirect:/projects_list/" + projectId;
    }



    @GetMapping("/projects_list/delete_project/{project_id}")
    public String deleteProject(@PathVariable("project_id") Long projectId) {
        projectService.deleteProject(projectId);
        return "redirect:/projects_list/";
    }

    @GetMapping("/projects_list/create_project")
    public String createProject() {
        projectService.createProject();
        return "redirect:/projects_list/";
    }
}
