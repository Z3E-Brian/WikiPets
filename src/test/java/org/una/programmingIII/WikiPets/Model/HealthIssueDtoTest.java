package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthIssueDtoTest {

    @Test
    public void noArgsConstructorTest() {
        HealthIssueDto healthIssueDto = new HealthIssueDto();

        assertNotNull(healthIssueDto);
        assertNull(healthIssueDto.getId());
        assertNull(healthIssueDto.getName());
        assertNull(healthIssueDto.getDescription());
        assertNull(healthIssueDto.getSymptoms());
        assertNull(healthIssueDto.getTreatment());
        assertNull(healthIssueDto.getSuitableDogBreeds());
        assertNull(healthIssueDto.getSuitableCatBreeds());
    }

    @Test
    public void allArgsConstructorTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        CatBreedDto catBreedDto = new CatBreedDto();

        HealthIssueDto healthIssueDto = new HealthIssueDto(
                1L,
                "Obesity",
                "A condition where excess body fat negatively affects a pet's health",
                "Overeating",
                "Diet and exercise",
                Arrays.asList(dogBreedDto),
                Arrays.asList(catBreedDto)
        );

        assertEquals(1L, healthIssueDto.getId());
        assertEquals("Obesity", healthIssueDto.getName());
        assertEquals("A condition where excess body fat negatively affects a pet's health", healthIssueDto.getDescription());
        assertEquals("Overeating", healthIssueDto.getSymptoms());
        assertEquals("Diet and exercise", healthIssueDto.getTreatment());
        assertEquals(1, healthIssueDto.getSuitableDogBreeds().size());
        assertEquals(1, healthIssueDto.getSuitableCatBreeds().size());
    }

    @Test
    public void settersAndGettersTest() {
        HealthIssueDto healthIssueDto = new HealthIssueDto();
        DogBreedDto dogBreedDto = new DogBreedDto();
        CatBreedDto catBreedDto = new CatBreedDto();

        healthIssueDto.setId(1L);
        healthIssueDto.setName("Diabetes");
        healthIssueDto.setDescription("A chronic disease that affects how the body turns food into energy.");
        healthIssueDto.setSymptoms("Increased thirst");
        healthIssueDto.setTreatment("Insulin therapy");
        healthIssueDto.setSuitableDogBreeds(Arrays.asList(dogBreedDto));
        healthIssueDto.setSuitableCatBreeds(Arrays.asList(catBreedDto));

        assertEquals(1L, healthIssueDto.getId());
        assertEquals("Diabetes", healthIssueDto.getName());
        assertEquals("A chronic disease that affects how the body turns food into energy.", healthIssueDto.getDescription());
        assertEquals("Increased thirst", healthIssueDto.getSymptoms());
        assertEquals("Insulin therapy", healthIssueDto.getTreatment());
        assertEquals(1, healthIssueDto.getSuitableDogBreeds().size());
        assertEquals(1, healthIssueDto.getSuitableCatBreeds().size());
    }

    @Test
    public void equalsAndHashCodeTest() {
        DogBreedDto dogBreedDto = new DogBreedDto();
        CatBreedDto catBreedDto = new CatBreedDto();

        HealthIssueDto healthIssueDto1 = new HealthIssueDto(
                1L,
                "Arthritis",
                "Inflammation of the joints causing pain and stiffness.",
                "Limping",
                "Anti-inflammatory drugs",
                Arrays.asList(dogBreedDto),
                Arrays.asList(catBreedDto)
        );

        HealthIssueDto healthIssueDto2 = new HealthIssueDto(
                1L,
                "Arthritis",
                "Inflammation of the joints causing pain and stiffness.",
                "Limping",
                "Anti-inflammatory drugs",
                Arrays.asList(dogBreedDto),
                Arrays.asList(catBreedDto)
        );

        assertEquals(healthIssueDto1, healthIssueDto2);
        assertEquals(healthIssueDto1.hashCode(), healthIssueDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        HealthIssueDto healthIssueDto = new HealthIssueDto();
        healthIssueDto.setId(1L);
        healthIssueDto.setName("Allergies");
        healthIssueDto.setDescription("An abnormal response of the immune system to allergens.");
        healthIssueDto.setSymptoms("Itchy skin");
        healthIssueDto.setTreatment("Antihistamines");

        String result = healthIssueDto.toString();

        assertTrue(result.contains("1"));
        assertTrue(result.contains("Allergies"));
        assertTrue(result.contains("An abnormal response of the immune system to allergens."));
        assertTrue(result.contains("Itchy skin"));
        assertTrue(result.contains("Antihistamines"));
    }
}
