package com.saga.executor.models.utils;

import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Data
public class Address {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Version
    private long version;

    private String number;
    private String appartment;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}
