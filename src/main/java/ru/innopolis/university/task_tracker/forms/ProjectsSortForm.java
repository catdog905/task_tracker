package ru.innopolis.university.task_tracker.forms;

import lombok.Data;

@Data
public class ProjectsSortForm {
    private ProjectSortBy projectSortBy;
    private HowSort howSort;
}
