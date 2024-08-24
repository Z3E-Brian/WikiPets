package org.una.programmingIII.WikiPets.Mapper;

public interface GenericMapper<E, D> {
    D convertToDTO(E entity);
    E convertToEntity(D dto);
}