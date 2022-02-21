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
    private String name;
    private Date startDate;
    private Date completionDate;
    private ProjectStatus status;
    private int priority;

    @ElementCollection
    private List<Long> taskSet;
}
