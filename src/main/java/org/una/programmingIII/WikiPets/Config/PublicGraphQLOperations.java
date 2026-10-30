package org.una.programmingIII.WikiPets.Config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.Document;
import graphql.language.Field;
import graphql.language.OperationDefinition;
import graphql.language.Selection;
import graphql.parser.InvalidSyntaxException;
import graphql.parser.Parser;

import java.util.List;
import java.util.Set;

/**
 * Decides whether a GraphQL request body may run without a JWT.
 *
 * <p>The body is parsed (JSON and then the GraphQL document) instead of being searched as text,
 * so a comment, a string argument or an operation name that mentions "login" does not make a
 * request public. A request is public only when the operation that will actually be executed is a
 * mutation whose root fields are all in {@link #PUBLIC_MUTATIONS}.</p>
 */
public class PublicGraphQLOperations {
    static final Set<String> PUBLIC_MUTATIONS = Set.of("login", "initiateRecoverPassword", "recoverPassword");

    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean isPublic(String requestBody) {
        try {
            JsonNode request = objectMapper.readTree(requestBody);
            if (request == null || !request.isObject() || !request.path("query").isTextual()) {
                return false;
            }
            JsonNode operationName = request.path("operationName");
            if (!operationName.isMissingNode() && !operationName.isNull() && !operationName.isTextual()) {
                return false;
            }
            Document document = Parser.parse(request.get("query").asText());
            OperationDefinition operation = findOperationToExecute(document, operationName.textValue());
            return operation != null
                    && operation.getOperation() == OperationDefinition.Operation.MUTATION
                    && hasOnlyPublicRootFields(operation);
        } catch (JsonProcessingException | InvalidSyntaxException e) {
            return false;
        }
    }

    /**
     * Same rule GraphQL uses: with an operationName, the operation with that name; without one,
     * the document must contain a single operation.
     */
    private OperationDefinition findOperationToExecute(Document document, String operationName) {
        List<OperationDefinition> operations = document.getDefinitionsOfType(OperationDefinition.class);
        if (operationName == null) {
            return operations.size() == 1 ? operations.get(0) : null;
        }
        return operations.stream()
                .filter(operation -> operationName.equals(operation.getName()))
                .findFirst()
                .orElse(null);
    }

    private boolean hasOnlyPublicRootFields(OperationDefinition operation) {
        List<Selection> selections = operation.getSelectionSet().getSelections();
        return !selections.isEmpty() && selections.stream()
                .allMatch(selection -> selection instanceof Field field && PUBLIC_MUTATIONS.contains(field.getName()));
    }
}
