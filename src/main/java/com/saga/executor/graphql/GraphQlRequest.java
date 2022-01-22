package com.saga.executor.graphql;

import lombok.Data;

import java.util.Map;

@Data
public class GraphQlRequest {
    private String operationName;
    private String query;
    private Map<String, Object> variables;
}
