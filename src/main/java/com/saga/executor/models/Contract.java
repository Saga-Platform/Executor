package com.saga.executor.models;

import com.saga.executor.models.utils.ContractReview;
import lombok.Data;

import java.net.URI;

@Data
public class Contract {
    private String id;
    private InternshipApplication application;
    private URI location;

    private ContractReview review;
}
