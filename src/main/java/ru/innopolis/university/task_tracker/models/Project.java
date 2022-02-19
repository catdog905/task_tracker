package ru.innopolis.university.task_tracker.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name = "Template of the project";
    private Date startDate = new Date();
    private Date completionDate = new Date();
    private ProjectStatus status = ProjectStatus.NOT_STARTED;
    private int priority = 0;

    @OneToMany(mappedBy="project",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Task> taskSet;
}
