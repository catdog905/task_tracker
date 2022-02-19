package ru.innopolis.university.task_tracker.models;

import lombok.*;
import ru.innopolis.university.task_tracker.forms.TaskSubmitForm;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name = "Task template";
    private String description = "";
    private TaskStatus status = TaskStatus.TODO;
    private int priority = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private Project project;

    public Task(TaskSubmitForm taskSubmitForm) {
        name = taskSubmitForm.getName();
        description = taskSubmitForm.getDescription();
        status = taskSubmitForm.getStatus();
        priority = taskSubmitForm.getPriority();
    }
}
