package ru.innopolis.university.task_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.university.task_tracker.forms.ProjectSubmitForm;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.repositories.ProjectsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class ProjectsListController {
    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsListController(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    }

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/projects_list";
    }

    @GetMapping("/projects_list")
    public String getProjectListPage(ModelMap modelMap) {
        modelMap.addAttribute("projects", projectsRepository.findAll());
        return "projects_list";
    }

    @GetMapping("/projects_list/{project_id}")
    public String getProjectPage(@PathVariable("project_id") Long projectId, ModelMap modelMap) {
        try {
            modelMap.addAttribute("project",
                    projectsRepository.findById(projectId).orElseThrow(IndexOutOfBoundsException::new));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return "redirect:/error/no_such_project";
        }
        return "project_info";
    }

    @PostMapping("/projects_list/{project_id}/submit_data")
    public String submitData(@PathVariable("project_id") Long projectId, ProjectSubmitForm projectSubmitForm) throws ParseException {
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
        return "redirect:/projects_list/" + projectId;
    }



    @GetMapping("/projects_list/delete_project/{project_id}")
    public String deleteProject(@PathVariable("project_id") Long projectId) {
        projectsRepository.deleteById(projectId);
        return "redirect:/projects_list/";
    }

    @GetMapping("/projects_list/create_project")
    public String deleteProject() {
        Project project = new Project();
        projectsRepository.save(project);
        return "redirect:/projects_list/";
    }
}
