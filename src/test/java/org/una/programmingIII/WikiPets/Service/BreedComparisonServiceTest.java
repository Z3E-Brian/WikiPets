package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.una.programmingIII.WikiPets.Model.CatBreed;
import org.una.programmingIII.WikiPets.Model.DogBreed;
import org.una.programmingIII.WikiPets.Model.BreedComparisonResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BreedComparisonServiceTest {

    private BreedComparisonService breedComparisonService;

    private CatBreed catBreed1;
    private CatBreed catBreed2;
    private DogBreed dogBreed1;
    private DogBreed dogBreed2;

    @BeforeEach
    void setUp() {
        breedComparisonService = new BreedComparisonService();

        catBreed1 = new CatBreed();
        catBreed1.setName("Siamese");
        catBreed1.setOrigin("Thailand");
        catBreed1.setSize(5);
        catBreed1.setCoat("Short");
        catBreed1.setColor("Cream");
        catBreed1.setLifeSpan("15 years");
        catBreed1.setTemperament("Affectionate");
        catBreed1.setDescription("A sociable cat.");

        catBreed2 = new CatBreed();
        catBreed2.setName("Siamese");
        catBreed2.setOrigin("Thailand");
        catBreed2.setSize(5);
        catBreed2.setCoat("Short");
        catBreed2.setColor("Cream");
        catBreed2.setLifeSpan("15 years");
        catBreed2.setTemperament("Affectionate");
        catBreed2.setDescription("A sociable cat.");

        dogBreed1 = new DogBreed();
        dogBreed1.setName("Labrador");
        dogBreed1.setOrigin("Canada");
        dogBreed1.setSize(10);
        dogBreed1.setCoat("Short");
        dogBreed1.setColor("Black");
        dogBreed1.setLifeSpan("12 years");
        dogBreed1.setTemperament("Friendly");
        dogBreed1.setDescription("A loyal dog.");

        dogBreed2 = new DogBreed();
        dogBreed2.setName("Labrador");
        dogBreed2.setOrigin("Canada");
        dogBreed2.setSize(10);
        dogBreed2.setCoat("Short");
        dogBreed2.setColor("Black");
        dogBreed2.setLifeSpan("12 years");
        dogBreed2.setTemperament("Friendly");
        dogBreed2.setDescription("A loyal dog.");
    }

    @Test
    void testCompareCatBreeds_Same() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(catBreed1, catBreed2);

        assertTrue(result.getSimilarities().contains("Name: Siamese"));
        assertTrue(result.getSimilarities().contains("Origin: Thailand"));
        assertTrue(result.getSimilarities().contains("Size: 5"));
        assertTrue(result.getSimilarities().contains("Coat: Short"));
        assertTrue(result.getSimilarities().contains("Color: Cream"));
        assertTrue(result.getSimilarities().contains("Life Span: 15 years"));
        assertTrue(result.getSimilarities().contains("Temperament: Affectionate"));
        assertTrue(result.getSimilarities().contains("Description: A sociable cat."));
        assertTrue(result.getDifferences().isEmpty());
    }

    @Test
    void testCompareDogBreeds_Same() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(dogBreed1, dogBreed2);

        assertTrue(result.getSimilarities().contains("Name: Labrador"));
        assertTrue(result.getSimilarities().contains("Origin: Canada"));
        assertTrue(result.getSimilarities().contains("Size: 10"));
        assertTrue(result.getSimilarities().contains("Coat: Short"));
        assertTrue(result.getSimilarities().contains("Color: Black"));
        assertTrue(result.getSimilarities().contains("Life Span: 12 years"));
        assertTrue(result.getSimilarities().contains("Temperament: Friendly"));
        assertTrue(result.getSimilarities().contains("Description: A loyal dog."));
        assertTrue(result.getDifferences().isEmpty());

    }

    @Test
    void testCompareMixedBreeds_DogAndCat() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(dogBreed1, catBreed1);
        assertTrue(result.getDifferences().contains("Name: Labrador vs Siamese"));
        assertTrue(result.getDifferences().contains("Origin: Canada vs Thailand"));
        assertTrue(result.getDifferences().contains("Size: 10 vs 5"));
        assertTrue(result.getSimilarities().contains("Coat: Short"));
        assertTrue(result.getDifferences().contains("Color: Black vs Cream"));
        assertTrue(result.getDifferences().contains("Life Span: 12 years vs 15 years"));
        assertTrue(result.getDifferences().contains("Temperament: Friendly vs Affectionate"));
        assertTrue(result.getDifferences().contains("Description: A loyal dog. vs A sociable cat."));
        assertTrue(!result.getSimilarities().isEmpty());
    }

    @Test
    void testCompareMixedBreeds_CatAndDog() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(catBreed1, dogBreed1);
        assertTrue(result.getDifferences().contains("Name: Labrador vs Siamese"));
        assertTrue(result.getDifferences().contains("Origin: Canada vs Thailand"));
        assertTrue(result.getDifferences().contains("Size: 10 vs 5"));
        assertTrue(result.getSimilarities().contains("Coat: Short"));
        assertTrue(result.getDifferences().contains("Color: Black vs Cream"));
        assertTrue(result.getDifferences().contains("Life Span: 12 years vs 15 years"));
        assertTrue(result.getDifferences().contains("Temperament: Friendly vs Affectionate"));
        assertTrue(result.getDifferences().contains("Description: A loyal dog. vs A sociable cat."));
        assertTrue(!result.getSimilarities().isEmpty());
    }

    @Test
    void testCompareBreeds_NullInputs() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(null, null);
        assertNotNull(result);
        assertTrue(result.getSimilarities().isEmpty());
        assertTrue(result.getDifferences().isEmpty());
    }

    @Test
    void testCompareBreeds_DifferentTypes() {
        BreedComparisonResult result = breedComparisonService.compareBreeds(dogBreed1, "Some string");
        assertNotNull(result);
        assertTrue(result.getSimilarities().isEmpty());
        assertTrue(result.getDifferences().isEmpty());
    }

    @Test
    void testCompareCatBreeds() {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();
        breedComparisonService.compareCatBreeds(catBreed1, catBreed2, similarities, differences);

        assertTrue(similarities.contains("Name: Siamese"));
        assertTrue(differences.isEmpty());
    }

    @Test
    void testCompareDogBreeds() {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();
        breedComparisonService.compareDogBreeds(dogBreed1, dogBreed2, similarities, differences);

        assertTrue(similarities.contains("Name: Labrador"));
        assertTrue(differences.isEmpty());
    }

    @Test
    void testCompareMixedBreeds() {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();
        breedComparisonService.compareMixedBreeds(dogBreed1, catBreed1, similarities, differences);

        assertTrue(differences.contains("Name: Labrador vs Siamese"));
        assertTrue(!similarities.isEmpty());
    }

    @Test
    void testCompareFields_Similar() {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();
        breedComparisonService.compareFields("Field1", "Field1", "TestField", similarities, differences);

        assertTrue(similarities.contains("TestField: Field1"));
        assertTrue(differences.isEmpty());
    }

    @Test
    void testCompareFields_Different() {
        List<String> similarities = new ArrayList<>();
        List<String> differences = new ArrayList<>();
        breedComparisonService.compareFields("Field1", "Field2", "TestField", similarities, differences);

        assertTrue(differences.contains("TestField: Field1 vs Field2"));
        assertTrue(similarities.isEmpty());
    }
}
