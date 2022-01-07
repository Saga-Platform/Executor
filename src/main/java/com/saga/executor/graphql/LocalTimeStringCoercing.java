package com.saga.executor.graphql;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeStringCoercing implements Coercing<LocalTime, String> {

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalTime) {
            return ((LocalTime) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_TIME);
        } else {
            throw new CoercingSerializeException("Cannot coerce " + dataFetcherResult.getClass() + " into a ISO8601 LocalTime");
        }
    }

    @Override
    public LocalTime parseValue(Object input) throws CoercingParseValueException {
        try {
            return LocalTime.parse(input.toString());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }
    }

    @Override
    public LocalTime parseLiteral(Object input) throws CoercingParseLiteralException {
        try {
            return LocalTime.parse(((StringValue) input).getValue());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }

    }
}
