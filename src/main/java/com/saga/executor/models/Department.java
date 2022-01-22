package com.saga.executor.models;

import lombok.Value;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Value
public class Department {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    String id;

    String name;
}
