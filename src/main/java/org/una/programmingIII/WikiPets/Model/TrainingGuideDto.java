package org.una.programmingIII.WikiPets.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingGuideDto {

    private Long id;
    private String title;
    private String content;
    private List<CatBreedDto> catsBreedDto; // DTO para CatBreed, puede ser null si la guia de entreno es para un perro
    private List<DogBreedDto> dogsBreedDto; // DTO para DogBreed, puede ser null si la guia de entreno es para un gato

//    public TrainingGuideDto(TrainingGuide trainingGuide) {
//        this.id = trainingGuide.getId();
//        this.title = trainingGuide.getTitle();
//        this.content = trainingGuide.getContent();
//        this.catsBreedDto = trainingGuide.getTrainingGuideForCatsBreedsDto();
//        this.dogsBreedDto = trainingGuide.getTrainingGuideForDogBreedsDto();
//    }
//
//    public List<CatBreed> getTrainingGuideForCatsBreeds() {
//        List<CatBreed> favoriteCatBreeds = new ArrayList<>();
//        for (CatBreedDto catBreedDto : this.catsBreedDto) {
//            favoriteCatBreeds.add(new CatBreed(catBreedDto));
//        }
//        return favoriteCatBreeds;
//    }
//
//    public List<DogBreed> getTrainingGuideForDogsBreeds() {
//        List<DogBreed> favoriteDogBreeds = new ArrayList<>();
//        for (DogBreedDto dogBreedDto : this.dogsBreedDto) {
//            favoriteDogBreeds.add(new DogBreed(dogBreedDto));
//        }
//        return favoriteDogBreeds;
//    }

}