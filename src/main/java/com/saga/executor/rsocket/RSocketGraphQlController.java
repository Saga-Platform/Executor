package com.saga.executor.rsocket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import graphql.ExecutionResult;
import graphql.execution.ExecutionId;
import org.springframework.graphql.GraphQlService;
import org.springframework.graphql.RequestInput;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
public class RSocketGraphQlController {

    private final GraphQlService graphQlSvc;
    private final ObjectMapper mapper;

    public RSocketGraphQlController(GraphQlService graphQlSvc, ObjectMapper mapper) {
        this.graphQlSvc = graphQlSvc;
        this.mapper = mapper;
    }



    @MessageMapping("graphql")
    public Mono<ExecutionResult> executeGraphqlOverRSocket(@Payload ObjectNode payload) {
        var query = payload.get("query").asText("");
        var opName = payload.get("operationName").asText(null);
        var vars = mapper.convertValue(payload.get("variables"), new TypeReference<Map<String, Object>>(){});

        return graphQlSvc.execute(new RequestInput(query, opName, vars, null, ExecutionId.generate().toString()));
    }
}
