package com.saga.executor.models.users;

import com.saga.executor.models.Company;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@EqualsAndHashCode(callSuper = true)
public class Supervisor extends User {

    @Relationship("represents")
    private Company company;
}
