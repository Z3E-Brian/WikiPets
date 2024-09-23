//package org.una.programmingIII.WikiPets.Model;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class HealthIssueTest {
//    @Test
//    public void noArgsConstructorTest() {
//        HealthIssue healthIssue = new HealthIssue();
//
//        assertNotNull(healthIssue);
//        assertNull(healthIssue.getId());
//        assertNull(healthIssue.getName());
//        assertNull(healthIssue.getDescription());
//        assertNull(healthIssue.getSymptoms());
//        assertNull(healthIssue.getTreatment());
//        assertNull(healthIssue.getSuitableDogBreeds());
//        assertNull(healthIssue.getSuitableCatBreeds());
//    }
//
//    @Test
//    public void allArgsConstructorTest() {
//        DogBreed dogBreed = new DogBreed();
//        CatBreed catBreed = new CatBreed();
//
//        HealthIssue healthIssue = new HealthIssue(
//                1L,
//                "Obesity",
//                "A condition where excess body fat negatively affects a pet's health",
//                Arrays.asList(dogBreed),
//                Arrays.asList(catBreed),
//                "Overeating",
//                "Diet and exercise"
//        );
//
//        assertEquals(1L, healthIssue.getId());
//        assertEquals("Obesity", healthIssue.getName());
//        assertEquals("A condition where excess body fat negatively affects a pet's health", healthIssue.getDescription());
//        assertEquals("Overeating", healthIssue.getSymptoms());
//        assertEquals("Diet and exercise", healthIssue.getTreatment());
//        assertEquals(1, healthIssue.getSuitableDogBreeds().size());
//        assertEquals(1, healthIssue.getSuitableCatBreeds().size());
//    }
//
//    @Test
//    public void settersAndGettersTest() {
//        HealthIssue healthIssue = new HealthIssue();
//        DogBreed dogBreed = new DogBreed();
//        CatBreed catBreed = new CatBreed();
//
//        healthIssue.setId(1L);
//        healthIssue.setName("Diabetes");
//        healthIssue.setDescription("A chronic disease that affects how the body turns food into energy.");
//        healthIssue.setSuitableDogBreeds(Arrays.asList(dogBreed));
//        healthIssue.setSuitableCatBreeds(Arrays.asList(catBreed));
//        healthIssue.setSymptoms("Increased thirst");
//        healthIssue.setTreatment("Insulin therapy");
//
//        assertEquals(1L, healthIssue.getId());
//        assertEquals("Diabetes", healthIssue.getName());
//        assertEquals("A chronic disease that affects how the body turns food into energy.", healthIssue.getDescription());
//        assertEquals("Increased thirst", healthIssue.getSymptoms());
//        assertEquals("Insulin therapy", healthIssue.getTreatment());
//        assertEquals(1, healthIssue.getSuitableDogBreeds().size());
//        assertEquals(1, healthIssue.getSuitableCatBreeds().size());
//    }
//
//    @Test
//    public void equalsAndHashCodeTest() {
//
//        DogBreed dogBreed = new DogBreed();
//        CatBreed catBreed = new CatBreed();
//
//        HealthIssue healthIssue1 = new HealthIssue(
//                1L,
//                "Arthritis",
//                "Inflammation of the joints causing pain and stiffness.",
//                Arrays.asList(dogBreed),
//                Arrays.asList(catBreed),
//                "Limping",
//                "Anti-inflammatory drugs"
//        );
//
//        HealthIssue healthIssue2 = new HealthIssue(
//                1L,
//                "Arthritis",
//                "Inflammation of the joints causing pain and stiffness.",
//                Arrays.asList(dogBreed),
//                Arrays.asList(catBreed),
//                "Limping",
//                "Anti-inflammatory drugs"
//        );
//
//        assertEquals(healthIssue1, healthIssue2);
//        assertEquals(healthIssue1.hashCode(), healthIssue2.hashCode());
//    }
//
//    @Test
//    public void toStringTest() {
//        HealthIssue healthIssue = new HealthIssue();
//        healthIssue.setId(1L);
//        healthIssue.setName("Allergies");
//        healthIssue.setDescription("An abnormal response of the immune system to allergens.");
//        healthIssue.setSymptoms("Itchy skin");
//        healthIssue.setTreatment("Antihistamines");
//
//
//        String result = healthIssue.toString();
//
//        assertTrue(result.contains("1"));
//        assertTrue(result.contains("Allergies"));
//        assertTrue(result.contains("An abnormal response of the immune system to allergens."));
//        assertTrue(result.contains("Itchy skin"));
//        assertTrue(result.contains("Antihistamines"));
//    }
//}
