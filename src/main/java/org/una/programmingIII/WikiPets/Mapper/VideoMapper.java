package org.una.programmingIII.WikiPets.Mapper;

import org.mapstruct.factory.Mappers;
import org.una.programmingIII.WikiPets.Model.Video;
import org.una.programmingIII.WikiPets.Model.VideoDto;

public interface VideoMapper {
    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);
    VideoDto toImageDto(Video video);
    Video toImageIssue(VideoDto videoDto);
}
