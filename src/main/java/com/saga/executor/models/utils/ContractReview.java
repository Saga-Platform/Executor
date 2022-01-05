package com.saga.executor.models.utils;

import lombok.Data;

@Data
public class ContractReview {
    private ContractState status;
    private String reason;
}
