package com.saga.executor.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Monitor extends User {
    private List<Department> departments;
}
