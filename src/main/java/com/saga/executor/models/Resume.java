package com.saga.executor.models;

import com.saga.executor.models.users.Student;
import com.saga.executor.models.utils.SimpleReview;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.net.URI;
import java.util.UUID;

@Node
@Data
public class Resume {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;

    @Relationship("ownedBy")
    private Student owner;

    private String displayName;
    private URI location;
    private SimpleReview review;
}
