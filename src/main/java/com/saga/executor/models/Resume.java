package com.saga.executor.models;

import com.saga.executor.models.users.Student;
import com.saga.executor.models.utils.SimpleReview;
import lombok.Data;

import java.net.URI;

@Data
public class Resume {
    private String id;
    private Student owner;
    private String displayName;
    private URI location;
    private SimpleReview review;
}
