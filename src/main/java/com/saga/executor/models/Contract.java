package com.saga.executor.models;

import com.saga.executor.models.utils.ContractReview;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.net.URI;
import java.util.UUID;

@Node
@Data
public class Contract {

    @Id
    @GeneratedValue(GeneratedValue.UUIDGenerator.class)
    private UUID id;

    @Relationship("confirms")
    private InternshipApplication application;

    private URI location;

    private ContractReview review;
}
