package org.una.programmingIII.WikiPets.Dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TrainingGuideDtoTest {

    private TrainingGuideDto trainingGuideDto;
    private CatBreedDto catBreedDto;
    private DogBreedDto dogBreedDto;

    @BeforeEach
    void setUp() {
        LocalDate nowDate = LocalDate.now();

        // Configuración para catBreedDto
        catBreedDto = new CatBreedDto();
        catBreedDto.setId(1L);
        catBreedDto.setName("Siamese");
        catBreedDto.setOrigin("Thailand");
        catBreedDto.setSize(5);
        catBreedDto.setCoat("Short");
        catBreedDto.setColor("Cream with darker points");
        catBreedDto.setLifeSpan("12-20 years");
        catBreedDto.setTemperament("Social, Affectionate");
        catBreedDto.setDescription("Siamese cats are known for their striking appearance and vocal personality.");
        catBreedDto.setCreatedDate(nowDate);
        catBreedDto.setModifiedDate(nowDate);

        // Configuración para dogBreedDto
        dogBreedDto = new DogBreedDto();
        dogBreedDto.setId(1L);
        dogBreedDto.setName("Labrador");
        dogBreedDto.setOrigin("Canada");
        dogBreedDto.setSize(3);
        dogBreedDto.setCoat("Short");
        dogBreedDto.setColor("Yellow");
        dogBreedDto.setLifeSpan("10-12 years");
        dogBreedDto.setTemperament("Friendly, Active, Outgoing");
        dogBreedDto.setDescription("One of the most popular breeds in the world.");
        dogBreedDto.setCreatedDate(nowDate);
        dogBreedDto.setModifiedDate(nowDate);

        // Creando listas de razas
        List<CatBreedDto> catBreeds = new ArrayList<>();
        catBreeds.add(catBreedDto);

        List<DogBreedDto> dogBreeds = new ArrayList<>();
        dogBreeds.add(dogBreedDto);

        // Configuración de TrainingGuideDto
        trainingGuideDto = new TrainingGuideDto();
        trainingGuideDto.setId(1L);
        trainingGuideDto.setTitle("Training Your Pet");
        trainingGuideDto.setContent("A comprehensive guide to training your pet.");
        trainingGuideDto.setCatBreeds(catBreeds);
        trainingGuideDto.setDogBreeds(dogBreeds);
        trainingGuideDto.setCreateDate(nowDate);
        trainingGuideDto.setLastUpdate(nowDate);
    }

    @Test
    public void argsGettersTest() {
        assertEquals(1L, trainingGuideDto.getId());
        assertEquals("Training Your Pet", trainingGuideDto.getTitle());
        assertEquals("A comprehensive guide to training your pet.", trainingGuideDto.getContent());
        assertEquals("Siamese", trainingGuideDto.getCatBreeds().get(0).getName());
        assertEquals("Labrador", trainingGuideDto.getDogBreeds().get(0).getName());
    }

    @Test
    public void argsSettersTest() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> newCatBreeds = new ArrayList<>();
        CatBreedDto newCatBreed = new CatBreedDto(2L, "Persian", "Iran", 4, "Long", "White", "10-15 years", "Calm, Affectionate", "Known for its long hair and flat face.", nowDate, nowDate, null, null, null, null, null, null, null, null, null, null, null, null);
        newCatBreeds.add(newCatBreed);

        List<DogBreedDto> newDogBreeds = new ArrayList<>();
        DogBreedDto newDogBreed = new DogBreedDto(2L, "German Shepherd", "Germany", 4, "Medium", "Black and Tan", "9-13 years", "Intelligent, Loyal, Courageous", "Popular as a working dog.", nowDate, nowDate, null, null, null, null, null, null, null, null, null, null, null, null);
        newDogBreeds.add(newDogBreed);

        trainingGuideDto.setId(2L);
        trainingGuideDto.setTitle("Advanced Pet Training");
        trainingGuideDto.setContent("An advanced guide to training your pet.");
        trainingGuideDto.setCatBreeds(newCatBreeds);
        trainingGuideDto.setDogBreeds(newDogBreeds);

        assertEquals(2L, trainingGuideDto.getId());
        assertEquals("Advanced Pet Training", trainingGuideDto.getTitle());
        assertEquals("An advanced guide to training your pet.", trainingGuideDto.getContent());
        assertEquals("Persian", trainingGuideDto.getCatBreeds().get(0).getName());
        assertEquals("German Shepherd", trainingGuideDto.getDogBreeds().get(0).getName());
    }

    @Test
    public void equalsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        List<CatBreedDto> catBreeds1 = new ArrayList<>();
        catBreeds1.add(catBreedDto);

        List<DogBreedDto> dogBreeds1 = new ArrayList<>();
        dogBreeds1.add(dogBreedDto);

        TrainingGuideDto trainingGuideDto1 = new TrainingGuideDto(1L, "Training Your Pet", "A comprehensive guide to training your pet.", catBreeds1, dogBreeds1, nowDate, nowDate);
        TrainingGuideDto trainingGuideDto2 = new TrainingGuideDto(1L, "Training Your Pet", "A comprehensive guide to training your pet.", catBreeds1, dogBreeds1, nowDate, nowDate);

        assertEquals(trainingGuideDto1, trainingGuideDto2);
        assertEquals(trainingGuideDto1.hashCode(), trainingGuideDto2.hashCode());
    }

    @Test
    public void notEqualsAndHashCodeTest() {
        LocalDate nowDate = LocalDate.now();

        TrainingGuideDto trainingGuideDto1 = new TrainingGuideDto(1L, "Training Your Pet", "A comprehensive guide to training your pet.", new ArrayList<>(), new ArrayList<>(), nowDate, nowDate);
        TrainingGuideDto trainingGuideDto2 = new TrainingGuideDto(2L, "Advanced Pet Training", "An advanced guide to training your pet.", new ArrayList<>(), new ArrayList<>(), nowDate, nowDate);

        assertNotEquals(trainingGuideDto1, trainingGuideDto2);
        assertNotEquals(trainingGuideDto1.hashCode(), trainingGuideDto2.hashCode());
    }

    @Test
    public void toStringTest() {
        LocalDate nowDate = LocalDate.now();

        String expected = "TrainingGuideDto(id=1, title=Training Your Pet, content=A comprehensive guide to training your pet., createDate=" + nowDate + ", lastUpdate=" + nowDate + ")";
        assertEquals(expected, trainingGuideDto.toString());
    }
}
