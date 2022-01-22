package com.saga.executor.models;

import com.saga.executor.models.utils.Address;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Data
public class Company {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    @Setter(AccessLevel.PRIVATE)
    private String id;

    @Version
    private long version;

    @Relationship("isLocatedAt")
    private Address address;

    private String name;
    private String phoneNumber;
}
