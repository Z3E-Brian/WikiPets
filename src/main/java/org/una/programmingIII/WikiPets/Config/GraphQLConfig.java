package org.una.programmingIII.WikiPets.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;


@Configuration
public class    GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("hello", env -> "Hello, World!")
                );
    }
}