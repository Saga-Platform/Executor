package com.saga.executor.models;

import com.saga.executor.models.users.Student;
import com.saga.executor.models.utils.SimpleReview;
import lombok.Value;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.net.URI;

@Node
@Value
public class Resume {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;

    @Relationship("ownedBy")
    Student owner;

    String displayName;
    URI location;

    @Relationship("reviewedBy")
    SimpleReview review;
}
