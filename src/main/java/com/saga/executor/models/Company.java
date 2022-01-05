package com.saga.executor.models;

import com.saga.executor.models.utils.Address;
import lombok.Data;

@Data
public class Company {
    private String id;
    private String name;
    private Address address;
    private String phoneNumber;
}
