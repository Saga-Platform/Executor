package com.saga.executor.models.users;

import com.saga.executor.models.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Monitor extends User {
    private List<Department> departments;
}
