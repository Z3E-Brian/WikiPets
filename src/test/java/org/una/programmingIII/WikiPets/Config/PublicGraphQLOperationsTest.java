package org.una.programmingIII.WikiPets.Config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicGraphQLOperationsTest {

    private final PublicGraphQLOperations publicGraphQLOperations = new PublicGraphQLOperations();

    @Test
    void publicMutationsArePublic() {
        assertTrue(publicGraphQLOperations.isPublic(
                "{\"query\":\"mutation { login(input: {email: \\\"a@b.c\\\", password: \\\"p\\\"}) { token } }\"}"));
        assertTrue(publicGraphQLOperations.isPublic(
                "{\"query\":\"mutation Recover($email: String!) { initiateRecoverPassword(email: $email) }\","
                        + "\"variables\":{\"email\":\"a@b.c\"}}"));
        assertTrue(publicGraphQLOperations.isPublic(
                "{\"query\":\"mutation { recoverPassword(input: {email: \\\"a@b.c\\\", password: \\\"p\\\", token: \\\"t\\\"}) }\"}"));
    }

    @Test
    void aliasDoesNotChangeTheExecutedField() {
        assertTrue(publicGraphQLOperations.isPublic("{\"query\":\"mutation { session: login(input: {}) { token } }\"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":\"mutation { login: deleteUser(id: 1) }\"}"));
    }

    @Test
    void publicMutationTogetherWithAnotherFieldIsNotPublic() {
        assertFalse(publicGraphQLOperations.isPublic(
                "{\"query\":\"mutation { login(input: {}) { token } deleteUser(id: 1) }\"}"));
    }

    @Test
    void queryWithAPublicFieldNameIsNotPublic() {
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":\"query { login { token } }\"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":\"{ login { token } }\"}"));
    }

    @Test
    void fragmentSpreadAtRootIsNotPublic() {
        assertFalse(publicGraphQLOperations.isPublic(
                "{\"query\":\"mutation { ...M } fragment M on Mutation { deleteUser(id: 1) }\"}"));
    }

    @Test
    void operationNameSelectsTheOperationThatIsChecked() {
        String document = "mutation Login { login(input: {}) { token } } mutation Delete { deleteUser(id: 1) }";

        assertTrue(publicGraphQLOperations.isPublic("{\"operationName\":\"Login\",\"query\":\"" + document + "\"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"operationName\":\"Delete\",\"query\":\"" + document + "\"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":\"" + document + "\"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"operationName\":\"Missing\",\"query\":\"" + document + "\"}"));
    }

    @Test
    void invalidRequestsAreNotPublic() {
        assertFalse(publicGraphQLOperations.isPublic(""));
        assertFalse(publicGraphQLOperations.isPublic("mutation { login(input: {}) { token } }"));
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":\"mutation { login(input: { \"}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"query\":42}"));
        assertFalse(publicGraphQLOperations.isPublic("{\"operationName\":1,\"query\":\"mutation { login(input: {}) { token } }\"}"));
        assertFalse(publicGraphQLOperations.isPublic("[{\"query\":\"mutation { login(input: {}) { token } }\"}]"));
    }
}
