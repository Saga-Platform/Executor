package com.saga.executor.graphql;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.net.URI;

public class UriStringCoercing implements Coercing<URI, String> {

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof URI) {
            return dataFetcherResult.toString();
        } else {
            throw new CoercingSerializeException("Cannot coerce " + dataFetcherResult.getClass() + " into an URI");
        }
    }

    @Override
    public URI parseValue(Object input) throws CoercingParseValueException {
        try {
            return new URI(input.toString());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }
    }

    @Override
    public URI parseLiteral(Object input) throws CoercingParseLiteralException {
        try {
            return new URI(input.toString());
        }
        catch (Exception e) {
            throw new CoercingParseValueException(e);
        }

    }
}
