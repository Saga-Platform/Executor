package com.saga.executor.models;

import com.saga.executor.models.utils.ApplicationState;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Node
@Data
public class InternshipApplication {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;

    @Relationship("appliesTo")
    private InternshipOffer internship;

    @Relationship("using")
    private Resume resume;

    private ApplicationState status;
    private String rejectionMsg;
}
