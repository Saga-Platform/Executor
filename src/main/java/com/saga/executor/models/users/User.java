package com.saga.executor.models;

import lombok.Data;

@Data
public abstract class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
