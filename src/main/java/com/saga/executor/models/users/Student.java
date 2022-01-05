package com.saga.executor.models.users;

import com.saga.executor.models.utils.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    private Address address;
    private List<String> semesters;
}
