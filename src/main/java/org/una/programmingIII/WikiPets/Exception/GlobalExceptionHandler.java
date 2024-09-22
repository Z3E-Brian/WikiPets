package org.una.programmingIII.WikiPets.Exception;

import graphql.schema.DataFetchingEnvironment;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import graphql.GraphQLError;

@Component
public class GlobalExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Autowired
    private ExceptionMessageProcessor exceptionMessageProcessor;

    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable ex, DataFetchingEnvironment env) {
        return exceptionMessageProcessor.processErrorMessage(ex, env);
    }
}