package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.Image;
import org.una.programmingIII.WikiPets.Model.ImageDto;

public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);
    ImageDto ToImageDto(Image image);
    Image DtoToImage(ImageDto imageDto);
}
