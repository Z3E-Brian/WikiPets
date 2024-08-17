package org.una.programmingIII.WikiPets.Model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdoptionCenterDtoTest {

    private AdoptionCenterDto adoptionCenterDto;
    private List<DogBreedDto> dogBreeds;
    private List<CatBreedDto> catBreeds;

    @BeforeEach
    public void setUp() {
        dogBreeds = new ArrayList<>();
        catBreeds = new ArrayList<>();
        adoptionCenterDto = new AdoptionCenterDto();
        adoptionCenterDto.setId(1L);
        adoptionCenterDto.setName("Happy Tails Adoption Center");
        adoptionCenterDto.setLocation("123 Pet Lane");
        adoptionCenterDto.setAvailableDogBreeds(dogBreeds);
        adoptionCenterDto.setAvailableCatBreeds(catBreeds);
        adoptionCenterDto.setCreateDate(LocalDate.now());
        adoptionCenterDto.setLastUpdate(LocalDate.now());
    }

    @Test
    public void testAdoptionCenterDtoCreation() {
        assertNotNull(adoptionCenterDto);
    }

    @Test
    public void testGetters() {
        assertEquals(1L, adoptionCenterDto.getId());
        assertEquals("Happy Tails Adoption Center", adoptionCenterDto.getName());
        assertEquals("123 Pet Lane", adoptionCenterDto.getLocation());
        assertEquals(dogBreeds, adoptionCenterDto.getAvailableDogBreeds());
        assertEquals(catBreeds, adoptionCenterDto.getAvailableCatBreeds());
        assertEquals(LocalDate.now(), adoptionCenterDto.getCreateDate());
        assertEquals(LocalDate.now(), adoptionCenterDto.getLastUpdate());
    }

    @Test
    public void testSetters() {
        List<DogBreedDto> newDogBreeds = new ArrayList<>();
        List<CatBreedDto> newCatBreeds = new ArrayList<>();
        LocalDate newDate = LocalDate.of(2022, 1, 1);

        adoptionCenterDto.setName("New Name");
        adoptionCenterDto.setLocation("456 New Lane");
        adoptionCenterDto.setAvailableDogBreeds(newDogBreeds);
        adoptionCenterDto.setAvailableCatBreeds(newCatBreeds);
        adoptionCenterDto.setCreateDate(newDate);
        adoptionCenterDto.setLastUpdate(newDate);

        assertEquals("New Name", adoptionCenterDto.getName());
        assertEquals("456 New Lane", adoptionCenterDto.getLocation());
        assertEquals(newDogBreeds, adoptionCenterDto.getAvailableDogBreeds());
        assertEquals(newCatBreeds, adoptionCenterDto.getAvailableCatBreeds());
        assertEquals(newDate, adoptionCenterDto.getCreateDate());
        assertEquals(newDate, adoptionCenterDto.getLastUpdate());
    }

    @Test
    public void testNoArgsConstructor() {
        AdoptionCenterDto dto = new AdoptionCenterDto();
        assertNotNull(dto);
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        List<DogBreedDto> newDogBreeds = new ArrayList<>();
        List<CatBreedDto> newCatBreeds = new ArrayList<>();
        AdoptionCenterDto dto = new AdoptionCenterDto(2L, "Another Center", "789 Another Lane", newDogBreeds, newCatBreeds, date, date);

        assertEquals(2L, dto.getId());
        assertEquals("Another Center", dto.getName());
        assertEquals("789 Another Lane", dto.getLocation());
        assertEquals(newDogBreeds, dto.getAvailableDogBreeds());
        assertEquals(newCatBreeds, dto.getAvailableCatBreeds());
        assertEquals(date, dto.getCreateDate());
        assertEquals(date, dto.getLastUpdate());
    }
}