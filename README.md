# WikiPets
 A GraphQL API wiki about cats and dogs. The application provides detailed information about different breeds of cats and dogs, their characteristics, care, health, feeding, behavior and more. The API allows users to access this information efficiently and from different clients.

## Stack

Java 21, Spring Boot 3.3, Spring for GraphQL, Spring Data JPA, PostgreSQL 16, Spring Security with JWT (jjwt) and Spring Mail. Tests use JUnit 5 and Mockito.

## Running it locally

Requirements: JDK 21 and Docker (with the Compose plugin).

1. Create your local configuration. `.env` is ignored by git; never commit real values.

   ```bash
   cp .env.example .env
   ```

   Fill in at least `POSTGRES_PASSWORD`, `PGADMIN_DEFAULT_PASSWORD`, `JWT_SECRET` (`openssl rand -base64 48`) and, to send password recovery emails, `MAIL_USERNAME` and `MAIL_PASSWORD` (a Gmail [app password](https://myaccount.google.com/apppasswords), not the account password).

2. Start PostgreSQL (port 6000) and pgAdmin (http://localhost:5051):

   ```bash
   docker compose up -d
   ```

3. Start the API. It reads `.env` from the working directory, and the same values can be given as environment variables instead (useful in production).

   ```bash
   ./mvnw spring-boot:run
   # with SQL and connection pool debug logs:
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```

The GraphQL endpoint is `POST http://localhost:8080/graphql` and GraphiQL is at http://localhost:8080/graphiql. Hibernate creates the tables on startup (`ddl-auto=update`); `Data.sql` has sample breeds.

## Authentication

Every request to `/graphql` needs an `Authorization: Bearer <token>` header, except these mutations:
`login`, `initiateRecoverPassword` and `recoverPassword`.

The request body is parsed to decide this, so only a request whose executed operation is made exclusively of those mutations is accepted without a token.

Tokens have a single purpose (`access`, `refresh` or `password_reset`) and are only accepted for it: a refresh token or a recovery token does not work as an access token.

`createUser` also requires a token, so the first user has to be inserted in the database with a BCrypt hash as password.

## Examples

Log in:

```graphql
mutation {
  login(input: { email: "user@example.com", password: "my-password" }) {
    token
    refreshToken
    user { id name }
  }
}
```

Query a paginated list (with the header `Authorization: Bearer <token>`):

```graphql
query {
  getAllGroomingGuides(page: 0, size: 5) {
    totalElements
    groomingGuides { id content toolsNeeded suitableDogBreeds { name } }
  }
}
```

Create an adoption center and link a breed:

```graphql
mutation {
  createAdoptionCenter(input: { name: "Happy Paws", location: "Heredia" }) { id }
}

mutation {
  addDogBreedInAdoptionCenter(id: 1, idDogBreed: 3) {
    name
    availableDogBreeds { name }
  }
}
```

Recover a password (no token needed). The first mutation always answers the same message, whether the account exists or not; the email contains a token valid for 15 minutes and only for that account:

```graphql
mutation { initiateRecoverPassword(email: "user@example.com") }

mutation {
  recoverPassword(input: { email: "user@example.com", password: "new-password", token: "<token from the email>" })
}
```

## Tests

```bash
./mvnw test
```

`WikiPetsApplicationTests` starts the whole application, so it needs the database from `docker compose up -d` and a filled `.env`.

## My part

This was a team project. My part was:

- The **GroomingGuide**, **BehaviorGuide**, **Image**, **Video** and **AdoptionCenter** modules: JPA model, GraphQL schema, service, controller (resolvers), DTO, input and mapper.
- **Password recovery**: `EmailService` and `initiatePasswordRecovery` / `completePasswordRecovery` in `AuthenticationService`.
- Unit tests (JUnit + Mockito) for those services.

After the course I also fixed several security issues: credentials moved out of the repository into environment variables, public operations detected by parsing the GraphQL request instead of searching text in the body, the JWT filter stops after answering 401, tokens with a single purpose, recovery tokens bound to their account, no request bodies or SQL parameters in the default logs, with tests for each fix.
