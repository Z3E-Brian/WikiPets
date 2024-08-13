package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Service.BreedComparisonService.BreedComparisonResult;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BreedComparisonServiceTest {

    @InjectMocks
    private BreedComparisonService breedComparisonService;

    private CatBreed catBreed;
    private DogBreed dogBreed;

    @BeforeEach
    void setUp() {
        // Configurar CatBreed y DogBreed para las pruebas
        catBreed = new CatBreed();
        catBreed.setName("Siamese");
        catBreed.setOrigin("Thailand");
        catBreed.setSize(2);
        catBreed.setCoat("Short");
        catBreed.setColor("Cream with points");
        catBreed.setLifeSpan("12-16 years");
        catBreed.setTemperament("Affectionate, Social, Vocal");
        catBreed.setDescription("Popular breed known for its striking appearance and vocal nature.");

        dogBreed = new DogBreed();
        dogBreed.setName("Beagle");
        dogBreed.setOrigin("United Kingdom");
        dogBreed.setSize(3);
        dogBreed.setCoat("Short");
        dogBreed.setColor("Tricolor");
        dogBreed.setLifeSpan("10-15 years");
        dogBreed.setTemperament("Friendly, Curious, Merry");
        dogBreed.setDescription("A breed known for its strong sense of smell and friendly disposition.");
    }

    @Test
    public void compareBreeds_withDifferences() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(dogBreed, catBreed);

        List<String> similarities = result.getSimilarities();
        List<String> differences = result.getDifferences();

        // Verificar similitudes
        assertNotNull(similarities);
        assertEquals(1, similarities.size());
        assertEquals("Coat: Short", similarities.get(0));

        // Verificar diferencias
        assertNotNull(differences);
        assertEquals(7, differences.size());
        assertEquals("Name: Beagle vs Siamese", differences.get(0));
        assertEquals("Origin: United Kingdom vs Thailand", differences.get(1));
        assertEquals("Size: 3 vs 2", differences.get(2));
        assertEquals("Color: Tricolor vs Cream with points", differences.get(3));
        assertEquals("Life Span: 10-15 years vs 12-16 years", differences.get(4));
        assertEquals("Temperament: Friendly, Curious, Merry vs Affectionate, Social, Vocal", differences.get(5));
        assertEquals("Description: A breed known for its strong sense of smell and friendly disposition. vs Popular breed known for its striking appearance and vocal nature.", differences.get(6));
    }

    @Test
    public void compareBreeds_withSimilarities() {
        // Modificar dogBreed para que coincida con catBreed
        dogBreed.setName("Siamese");
        dogBreed.setOrigin("Thailand");
        dogBreed.setSize(2);
        dogBreed.setColor("Cream with points");
        dogBreed.setLifeSpan("12-16 years");
        dogBreed.setTemperament("Affectionate, Social, Vocal");
        dogBreed.setDescription("Popular breed known for its striking appearance and vocal nature.");

        BreedComparisonResult result = breedComparisonService.compareBreeds(dogBreed, catBreed);

        List<String> similarities = result.getSimilarities();
        List<String> differences = result.getDifferences();

        // Verificar similitudes
        assertNotNull(similarities);
        assertEquals(8, similarities.size());
        assertEquals("Name: Siamese", similarities.get(0));
        assertEquals("Origin: Thailand", similarities.get(1));
        assertEquals("Size: 2", similarities.get(2));
        assertEquals("Coat: Short", similarities.get(3));
        assertEquals("Color: Cream with points", similarities.get(4));
        assertEquals("Life Span: 12-16 years", similarities.get(5));
        assertEquals("Temperament: Affectionate, Social, Vocal", similarities.get(6));

        // Verificar que no haya diferencias
        assertNotNull(differences);
        assertTrue(differences.isEmpty());
    }
}
