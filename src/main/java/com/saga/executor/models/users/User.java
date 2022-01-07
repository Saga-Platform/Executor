package com.saga.executor.models.users;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.Id;

@Data
public abstract class User {

    @Id
    private String id;

    @Version
    private long version;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
