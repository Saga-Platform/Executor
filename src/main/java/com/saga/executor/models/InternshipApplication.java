package com.saga.executor.models;

import com.saga.executor.models.utils.ApplicationState;
import lombok.Value;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Value
public class InternshipApplication {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;

    @Relationship("appliesTo")
    InternshipOffer internship;

    @Relationship("using")
    Resume resume;

    ApplicationState status = ApplicationState.WAITING_FOR_EMPLOYER;
    String rejectionMsg;
}
