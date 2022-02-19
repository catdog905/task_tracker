package ru.innopolis.university.task_tracker.forms;

import lombok.Data;

@Data
public class TasksSortForm {
    private TasksSortBy tasksSortBy;
    private HowSort howSort;
}
