package com.saga.executor.models;

import com.saga.executor.models.utils.ContractReview;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.net.URI;

@Node
@Data
public class Contract {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Version
    private long version;

    @Relationship("confirms")
    private InternshipApplication application;

    private URI location;

    private ContractReview review;
}
