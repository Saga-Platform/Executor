package com.saga.executor.models.users;

import com.saga.executor.models.Company;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Supervisor extends User {
    private Company company;
}
