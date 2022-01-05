package com.saga.executor.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Supervisor extends User {
    private Company company;
}
