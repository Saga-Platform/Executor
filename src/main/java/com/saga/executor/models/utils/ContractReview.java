package com.saga.executor.models.utils;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node
@Data
public class ContractReview {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Version
    private long version;

    private ContractState status = ContractState.WAITING_FOR_EMPLOYER;
    private String reason;
}
