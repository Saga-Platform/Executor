package com.saga.executor.models;

import com.saga.executor.models.users.Student;
import com.saga.executor.models.users.Supervisor;
import com.saga.executor.models.utils.SimpleReview;
import lombok.Data;

import java.net.URI;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class InternshipOffer {
    private String id;
    private Supervisor supervisor;
    private ZonedDateTime createdOn;
    private ZonedDateTime closesOn;
    private int openings;
    private Department department;

    private String title;
    private String description;
    private ZonedDateTime startsOn;
    private ZonedDateTime finishesOn;
    private float salary;
    private LocalTime workdayStartsAt;
    private LocalTime workdayFinishesAt;

    private URI attachedFile;

    private String semester;

    private SimpleReview review;

    private List<Student> visibleTo;
}
