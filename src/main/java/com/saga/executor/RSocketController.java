package com.saga.executor;

import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RSocketController {



    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType scalarType = GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("java.time.ZonedDateTime serialized as ISO8601")
                .coercing(new Coercing<ZonedDateTime, String>() {

                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof ZonedDateTime) {
                            return ((ZonedDateTime) dataFetcherResult).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                        } else {
                            throw new CoercingSerializeException("Cannot coerce " + dataFetcherResult.getClass() + " into a ISO8601 ZonedDateTime");
                        }
                    }

                    @Override
                    public ZonedDateTime parseValue(Object input) throws CoercingParseValueException {
                        return null;
                    }

                    @Override
                    public ZonedDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
                        return null;
                    }
                })
                .build();
        return wiringBuilder -> wiringBuilder.scalar(scalarType);
    }

//    private final GraphQlService
}
