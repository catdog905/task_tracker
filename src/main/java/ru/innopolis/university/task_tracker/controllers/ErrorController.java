package ru.innopolis.university.task_tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ErrorController {
    @GetMapping("/error/{error}")
    public String getErrorPage(@PathVariable("error") String error, ModelMap modelMap) {
        modelMap.addAttribute("error", error);
        return "error";
    }
}
