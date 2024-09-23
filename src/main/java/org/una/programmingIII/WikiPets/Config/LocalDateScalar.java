package org.una.programmingIII.WikiPets.Config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateScalar {

    public static final GraphQLScalarType INSTANCE = GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("A custom scalar that handles LocalDate")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof LocalDate) {
                        return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                    throw new IllegalArgumentException("Unable to serialize LocalDate");
                }

                @Override
                public LocalDate parseValue(Object input) {
                    if (input instanceof String) {
                        return LocalDate.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                    throw new IllegalArgumentException("Unable to parse LocalDate");
                }

                @Override
                public LocalDate parseLiteral(Object input) {
                    if (input instanceof StringValue) {
                        return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                    }
                    throw new IllegalArgumentException("Unable to parse LocalDate");
                }
            })
            .build();
}