package com.saga.executor.models.utils;

import lombok.Data;

@Data
public class SimpleReview {
    private SimpleState status;
    private String reason;
}
