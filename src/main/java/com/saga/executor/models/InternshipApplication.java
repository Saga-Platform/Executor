package com.saga.executor.models;

import com.saga.executor.models.utils.ApplicationState;
import lombok.Data;

@Data
public class InternshipApplication {
    private String id;
    private InternshipOffer internship;
    private Resume resume;

    private ApplicationState status;
    private String rejectionMsg;
}
