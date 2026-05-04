# WikiPets

## What This Is

A GraphQL API for a cat and dog care wiki. The system provides detailed information about dog and cat breeds, their characteristics, health issues, nutrition guides, care tips, behavior guides, grooming guides, training guides, images, videos, adoption centers, and user reviews. The API supports JWT authentication with role-based access (consult/mutate per module + auditor role). Built as a university assignment for Programación III course.

## Core Value

Users can query comprehensive care information for cat and dog breeds through a well-designed GraphQL API with proper authentication, pagination (up to 1M records), and role-based access control.

## Requirements

### Validated

- ✓ Spring Boot 3.3.2 project structure with Java 21
- ✓ GraphQL API setup with spring-boot-starter-graphql
- ✓ PostgreSQL database integration with Docker support
- ✓ JWT authentication infrastructure (jjwt library)
- ✓ Entity models for 15 domains (CatBreed, DogBreed, HealthIssue, NutritionGuide, CareTip, BehaviorGuide, GroomingGuide, TrainingGuide, Image, Video, AdoptionCenter, User, Review, BreedComparison, FeedingSchedule)
- ✓ Layer architecture: Controller (GraphQL) → Service → Repository → Database
- ✓ DTO pattern with GenericMapper for type conversions
- ✓ Unit tests with JUnit 5 + Mockito
- ✓ Pagination support in list endpoints

### Active

- [ ] Complete GraphQL schema definitions for all 15 modules
- [ ] Implement all CRUD operations for each entity with proper GraphQL queries/mutations
- [ ] Complete JWT authentication with login, token refresh, and role-based authorization per module
- [ ] Implement password encryption (BCrypt)
- [ ] Complete user registration and email verification flow
- [ ] Add Bean Validation (@Valid) to all inputs
- [ ] Implement pagination with record counting for datasets up to 1M records
- [ ] Add environment variable separation (.env / application.properties externalized)
- [ ] Complete Docker setup for PostgreSQL
- [ ] Add unit tests for all controllers, services, and repositories (100% coverage target)
- [ ] Implement security measures against at least 3 attack types (SQL injection, XSS, JWT attacks)
- [ ] Document design patterns used with UML diagrams
- [ ] Create dataset for testing with up to 1M records
- [ ] Document AI tool usage (if any) in project documentation
- [ ] Implement proper error handling with custom exceptions and clear messages
- [ ] Add @Transactional annotations to all write operations
- [ ] Fix N+1 query problems in entity relationships
- [ ] Implement @EntityGraph or JOIN FETCH for efficient data loading

### Out of Scope

- Web interface / frontend — only GraphQL API with schema and execution client (out of assignment scope)
- OAuth / Social login — only JWT with email/password (assignment requirement)
- Real-time features (WebSockets, subscriptions) — not required
- Mobile app — web client only for testing GraphQL
- Payment processing — not applicable to wiki concept
- Multi-language support — Spanish/English not required initially

## Context

**University Assignment Context:**
- Course: Programación III (Universidad Nacional Sede Regional Brunca)
- Professor: Máster Rubén Mora Vargas
- Group size: 2-3 students (this repo appears to be one student or a small team)
- Weight: 25% of course grade

**Technical Context:**
- Existing codebase already has Java 21 + Spring Boot 3.3.2 + GraphQL + PostgreSQL
- Docker Compose available for local database
- JWT infrastructure partially implemented (needs completion)
- 15 entity models defined with relationships
- Service layer uses Interface + Implementation pattern
- GenericMapper with factory for DTO ↔ Entity conversions
- Testing with JUnit 5 + Mockito (partial coverage)

**Grading Criteria (from PDF):**
- Solution design (25%): proper design patterns, modularization
- Efficacy (65%): all functional and non-functional requirements met
- Defense/presentation (10%): clear demonstration of understanding

**Negative notes (can subtract points):**
- Error handling and validations (-10% max)
- Code quality (-10% max)

**Key Requirements from Assignment:**
1. JWT authentication with roles per module (consult vs mutate vs auditor)
2. Password encryption
3. Environment variables separated from code
4. Dockerized database
5. Unit tests for ALL code
6. Pagination supporting up to 1M records
7. Security against at least 3 attack types
8. UML diagrams documenting design
9. AI tool usage documentation (if used)

## Constraints

- **Technology**: Java 21, Spring Boot 3.3.2, GraphQL, PostgreSQL — fixed by assignment
- **Authentication**: JWT with role-based access per module — mandatory
- **Database**: PostgreSQL with Docker — mandatory
- **Testing**: 100% unit test coverage expected — mandatory
- **Dataset**: Must handle up to 1 million records with pagination — performance requirement
- **Security**: Must document at least 3 security measures — mandatory
- **Design**: Must justify design patterns used — heavily weighted in grading
- **Team**: Equal participation validated via git log and defense — mandatory

## Key Decisions

| Decision | Rationale | Outcome |
|----------|-----------|---------|
| Layer architecture (Controller → Service → Repository) | Standard Spring Boot pattern, meets grading criteria for modularization | — Pending |
| Interface + Implementation for Services | Allows for easier testing with mocks, demonstrates design patterns | — Pending |
| GenericMapper with Factory | Reduces boilerplate, reusable across all entities | — Pending |
| JWT for authentication | Assignment requirement, stateless auth | — Pending completion |
| Lombok for entities | Reduces boilerplate code (@Data, @NoArgsConstructor, etc.) | ✓ Good |
| PostgreSQL with Docker | Assignment requirement for database | ✓ Good |
| GraphQL over REST | Assignment requirement, better client flexibility | ✓ Good |

---
*Last updated: 2026-05-03 after codebase mapping and project initialization*