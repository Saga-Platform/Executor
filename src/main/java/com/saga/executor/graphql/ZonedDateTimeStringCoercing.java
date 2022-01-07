package com.saga.executor.graphql;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class ZonedDateTimeStringCoercing implements Coercing<ZonedDateTime, String> {

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
        try {
            return ZonedDateTime.parse(input.toString());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }
    }

    @Override
    public ZonedDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
        try {
            return ZonedDateTime.parse(((StringValue) input).getValue());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }

    }
}
