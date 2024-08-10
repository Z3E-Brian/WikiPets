package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.DogBreedDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DogBreedRepositoryTest {

    @Mock
    private DogBreedRepository dogBreedRepository;

    private DogBreed dogBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dogBreed = new DogBreed();
        dogBreed.setId(1L);
        dogBreed.setName("Golden Retriever");
        dogBreed.setOrigin("Scotland");
        dogBreed.setSize(3);
        dogBreed.setCoat("Long");
        dogBreed.setColor("Golden");
        dogBreed.setLifeSpan("10-12 years");
        dogBreed.setTemperament("Intelligent, Friendly, Devoted");
        dogBreed.setDescription("Popular house dog");}

    @Test
    public void findByBreedNameTest() {
    when(dogBreedRepository.findByBreedName("Golden Retriever")).thenReturn(dogBreed);
    DogBreed result = dogBreedRepository.findByBreedName("Golden Retriever");

    assertEquals(1L, result.getId());
    assertEquals("Golden Retriever", result.getName());
    }
}
