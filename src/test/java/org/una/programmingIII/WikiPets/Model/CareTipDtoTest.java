package org.una.programmingIII.WikiPets.Model;

import org.junit.jupiter.api.Test;
import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
import org.una.programmingIII.WikiPets.Dto.DogBreedDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CareTipDtoTest {

    @Test
    void gettersAndSettersTest() {
        List<DogBreedDto> dogBreedIds = new ArrayList<DogBreedDto>();
        List<CatBreedDto> catBreedIds= new ArrayList<CatBreedDto>();
        CareTipDto careTipDto = new CareTipDto();
        careTipDto.setId(1L);
        careTipDto.setTitle("Title");
        careTipDto.setContent("Content");
        careTipDto.setRelevantDogBreeds(dogBreedIds);
        careTipDto.setRelevantCatBreeds(catBreedIds);

        assertEquals(1L, careTipDto.getId());
        assertEquals("Title", careTipDto.getTitle());
        assertEquals("Content", careTipDto.getContent());
        assertEquals(dogBreedIds, careTipDto.getRelevantDogBreeds());
        assertEquals(catBreedIds, careTipDto.getRelevantCatBreeds());

        careTipDto.setTitle("New Title");
        assertEquals("New Title", careTipDto.getTitle());
    }
}
