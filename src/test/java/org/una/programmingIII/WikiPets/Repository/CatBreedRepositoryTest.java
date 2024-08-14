package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.CatBreed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CatBreedRepositoryTest {

    @Mock
    private CatBreedRepository catBreedRepository;

    private CatBreed catBreed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        catBreed = new CatBreed();
        catBreed.setId(1L);
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with points");
        catBreed.setLifeSpan("12-16 years");
        catBreed.setTemperament("Affectionate, Social, Vocal");
        catBreed.setDescription("Popular breed known for its striking appearance and vocal nature.");}

    @Test
    public void findByBreedNameTest() {
        when(catBreedRepository.findByCatBreedName("Siamese")).thenReturn(catBreed);
        CatBreed result = catBreedRepository.findByCatBreedName("Siamese");

        assertEquals(1L, result.getId());
        assertEquals("Siamese", result.getName());
    }
}
