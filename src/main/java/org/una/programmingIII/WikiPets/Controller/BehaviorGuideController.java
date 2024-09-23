    package org.una.programmingIII.WikiPets.Controller;

    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.graphql.data.method.annotation.Argument;
    import org.springframework.graphql.data.method.annotation.MutationMapping;
    import org.springframework.graphql.data.method.annotation.QueryMapping;
    import org.springframework.stereotype.Controller;
    import org.una.programmingIII.WikiPets.Dto.CatBreedDto;
    import org.una.programmingIII.WikiPets.Dto.DogBreedDto;
    import org.una.programmingIII.WikiPets.Dto.BehaviorGuideDto;
    import org.una.programmingIII.WikiPets.Exception.NotFoundElementException;
    import org.una.programmingIII.WikiPets.Input.BehaviorGuideInput;
    import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
    import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
    import org.una.programmingIII.WikiPets.Service.BehaviorGuideService;
    import org.una.programmingIII.WikiPets.Exception.CustomException;

    import java.util.List;
    import java.util.Map;

    @RequiredArgsConstructor
    @Controller
    public class BehaviorGuideController {

        private final BehaviorGuideService behaviorGuideService;
        private final GenericMapper<BehaviorGuideInput, BehaviorGuideDto> behaviorGuideMapper;

        @Autowired
        public BehaviorGuideController(BehaviorGuideService behaviorGuideService, GenericMapperFactory mapperFactory) {
            this.behaviorGuideService = behaviorGuideService;
            this.behaviorGuideMapper = mapperFactory.createMapper(BehaviorGuideInput.class, BehaviorGuideDto.class);
        }

        @QueryMapping
        public Map<String, Object> getAllBehaviorGuides(@Argument int page, @Argument int size) {
            try {
                return behaviorGuideService.getAllBehaviorGuides(page, size);
            } catch (NotFoundElementException e) {
                throw new NotFoundElementException("Could not retrieve behavior guides");
            }
        }

        @QueryMapping
        public BehaviorGuideDto getBehaviorGuideById(@Argument Long id) {
                return behaviorGuideService.getBehaviorGuideById(id);
        }

        @QueryMapping
        public List<DogBreedDto> getBehaviorSuitableDogBreeds(@Argument Long id) {
                return behaviorGuideService.getBehaviorSuitableDogBreeds(id);
        }

        @QueryMapping
        public List<CatBreedDto> getBehaviorSuitableCatBreeds(@Argument Long id) {
                return behaviorGuideService.getBehaviorSuitableCatBreeds(id);
        }

        @MutationMapping
        public BehaviorGuideDto addSuitableDogBreedToBehaviorGuide(@Argument Long id, @Argument Long idDogBreed) {
                return behaviorGuideService.addSuitableDogBreedToBehaviorGuide(id, idDogBreed);
        }

        @MutationMapping
        public BehaviorGuideDto addSuitableCatBreedToBehaviorGuide(@Argument Long id, @Argument Long idCatBreed) {
                return behaviorGuideService.addSuitableCatBreedToBehaviorGuide(id, idCatBreed);
        }

        @MutationMapping
        public BehaviorGuideDto createBehaviorGuide(@Argument BehaviorGuideInput input) {
                BehaviorGuideDto behaviorGuideDto = convertToDto(input);
                return behaviorGuideService.createBehaviorGuide(behaviorGuideDto);
        }

        @MutationMapping
        public BehaviorGuideDto updateBehaviorGuide(@Argument BehaviorGuideInput input) {
                BehaviorGuideDto behaviorGuideDto = convertToDto(input);
                return behaviorGuideService.updateBehaviorGuide(behaviorGuideDto);
        }

        @MutationMapping
        public Boolean deleteBehaviorGuide(@Argument Long id) {
                return behaviorGuideService.deleteBehaviorGuide(id);
        }

        @MutationMapping
        public BehaviorGuideDto deleteSuitableCatBreedFromBehaviorGuide(@Argument Long id, @Argument Long catBreedId) {
                return behaviorGuideService.deleteSuitableCatBreedFromBehaviorGuide(id, catBreedId);
        }

        @MutationMapping
        public BehaviorGuideDto deleteSuitableDogBreedFromBehaviorGuide(@Argument Long id, @Argument Long dogBreedId) {
                return behaviorGuideService.deleteSuitableDogBreedFromBehaviorGuide(id, dogBreedId);
        }

        private BehaviorGuideDto convertToDto(BehaviorGuideInput behaviorGuideInput) {
            return behaviorGuideMapper.convertToDTO(behaviorGuideInput);
        }
    }
