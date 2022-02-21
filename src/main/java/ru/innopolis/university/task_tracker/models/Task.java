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
    private String name;
    private String description;
    private TaskStatus status;
    private int priority;
}
