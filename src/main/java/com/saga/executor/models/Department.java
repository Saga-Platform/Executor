package com.saga.executor.models;

import com.saga.executor.models.users.User;
import lombok.Data;

@Data
public class Department {
    private String name;
    private User manager;
}
