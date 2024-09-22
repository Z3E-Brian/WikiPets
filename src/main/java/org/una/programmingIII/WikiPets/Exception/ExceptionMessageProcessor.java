package org.una.programmingIII.WikiPets.Exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import graphql.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessageProcessor {

    public GraphQLError processErrorMessage(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof NotFoundElementException) {
            return buildError("Not Found: " + ex.getMessage(), ErrorType.DataFetchingException, env);
        } else if (ex instanceof InvalidInputException) {
            return buildError("Invalid input: " + ex.getMessage(), ErrorType.ValidationError, env);
        } else if (ex instanceof BlankInputException) {
            return buildError("Blank input: " + ex.getMessage(), ErrorType.InvalidSyntax, env);
        } else {
            return buildError("Internal Server Error: " + ex.getMessage(), ErrorType.DataFetchingException, env);
        }
    }

    private GraphQLError buildError(String message, ErrorType errorType, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(message)
                .errorType(errorType)
                .build();
    }
}
