package com.saga.executor.rsocket;

import com.saga.executor.graphql.GraphQlRequest;
import graphql.ExecutionResult;
import graphql.execution.ExecutionId;
import org.springframework.graphql.GraphQlService;
import org.springframework.graphql.RequestInput;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class RSocketGraphQlController {

    private final GraphQlService graphQlSvc;

    public RSocketGraphQlController(GraphQlService graphQlSvc) {
        this.graphQlSvc = graphQlSvc;
    }



    @MessageMapping("graphql")
    public Mono<ExecutionResult> executeGraphqlOverRSocket(@Payload GraphQlRequest payload) {
        RequestInput input = new RequestInput(
                payload.getQuery(),
                payload.getOperationName(),
                payload.getVariables(),
                null,
                ExecutionId.generate().toString()
        );

        return graphQlSvc.execute(input);
    }
}
