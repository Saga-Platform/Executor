package com.saga.executor.models.users;

import com.saga.executor.models.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
@EqualsAndHashCode(callSuper = true)
public class Monitor extends User {

    @Relationship("worksIn")
    private List<Department> departments;
}
