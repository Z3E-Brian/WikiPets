package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.CareTip;
import org.una.programmingIII.WikiPets.Model.CareTipDto;
@Mapper
public interface CareTipMapper {
    CareTipMapper INSTANCE = Mappers.getMapper(CareTipMapper.class);

    @Mapping(target = "relevantDogBreeds", source = "relevantDogBreeds")
    @Mapping(target = "relevantCatBreeds", source = "relevantCatBreeds")
    public CareTip toCareTip(CareTipDto careTipDto);

    @Mapping(target = "relevantDogBreeds", source = "relevantDogBreeds")
    @Mapping(target = "relevantCatBreeds", source = "relevantCatBreeds")
    public CareTipDto toCareTipDto(CareTip careTip);
}
