package com.saga.executor.models.utils;

import lombok.Data;

@Data
public class Address {
    private String number;
    private String appartment;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}
