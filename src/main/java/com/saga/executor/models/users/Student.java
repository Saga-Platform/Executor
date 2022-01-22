package com.saga.executor.models.users;

import com.saga.executor.models.Department;
import com.saga.executor.models.utils.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @Relationship("residesAt")
    private Address address;

    @Relationship("studiesIn")
    private Department department;

    private List<String> semesters;
}
