package com.saga.executor.graphql;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfiguration {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        var dateTimeScalar = GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("java.time.ZonedDateTime serialized as ISO8601 2011-12-03T10:15:30+01:00")
                .coercing(new ZonedDateTimeStringCoercing())
                .build();

        var timeScalar = GraphQLScalarType.newScalar()
                .name("Time")
                .description("java.time.LocalTime serialized as ISO8601 10:15:30.157946478")
                .coercing(new LocalTimeStringCoercing())
                .build();

        var uriScalar = GraphQLScalarType.newScalar()
                .name("URI")
                .description("java.net.URI serialized as string")
                .coercing(new UriStringCoercing())
                .build();

        return wiringBuilder -> wiringBuilder
                .scalar(dateTimeScalar)
                .scalar(timeScalar)
                .scalar(uriScalar);
    }
}
