package com.saga.executor.models;

import com.saga.executor.models.users.Student;
import com.saga.executor.models.users.Supervisor;
import com.saga.executor.models.utils.SimpleReview;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.net.URI;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Node
@Data
public class InternshipOffer {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Version
    private long version;

    @Relationship("managedBy")
    private Supervisor supervisor;

    private ZonedDateTime createdOn;
    private ZonedDateTime closesOn;
    private int openings;

    @Relationship("offeredTo")
    private Department department;

    private String title;
    private String description;
    private ZonedDateTime startsOn;
    private ZonedDateTime finishesOn;
    private double salary;
    private LocalTime workdayStartsAt;
    private LocalTime workdayFinishesAt;

    private URI attachedFile;

    private String semester;

    @Relationship("reviewedBy")
    private SimpleReview review;

    @Relationship(value = "canView", direction = Relationship.Direction.INCOMING)
    private List<Student> visibleTo;
}
