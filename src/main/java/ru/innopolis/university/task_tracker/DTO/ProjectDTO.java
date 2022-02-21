package ru.innopolis.university.task_tracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.innopolis.university.task_tracker.models.Project;
import ru.innopolis.university.task_tracker.models.ProjectStatus;
import ru.innopolis.university.task_tracker.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProjectDTO {
    Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date completionDate;
    private ProjectStatus status;
    private int priority;
    private List<Long> taskSet;
}
