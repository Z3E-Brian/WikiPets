package org.una.programmingIII.WikiPets.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.una.programmingIII.WikiPets.Dto.AdoptionCenterDto;
import org.una.programmingIII.WikiPets.Service.AdoptionCenterService;
import org.una.programmingIII.WikiPets.Exception.CustomException;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdoptionCenterController {
    private final AdoptionCenterService adoptionCenterService;

    @QueryMapping
    public List<AdoptionCenterDto> getAdoptionCenters() {
        try {
            return adoptionCenterService.getAllAdoptionCenters();
        } catch (Exception e) {
            throw new CustomException("Could not find adoption centers");
        }
    }

    @QueryMapping
    public AdoptionCenterDto getAdoptionCenterById(@Argument Long id) {
        try {
            return adoptionCenterService.getAdoptionCenterById(id);
        } catch (Exception e) {
            throw new CustomException("Could not find adoption center");
        }
    }

    @MutationMapping
    public AdoptionCenterDto createAdoptionCenter(@Argument String name,
                                                  @Argument String location) {
        try {
            return adoptionCenterService.createAdoptionCenter(new AdoptionCenterDto(name, location));
        } catch (Exception e) {
            throw new CustomException("Could not create adoption center");
        }
    }

    @MutationMapping
    public AdoptionCenterDto updateAdoptionCenter(@Argument Long id,@Argument String name, @Argument String location) {
        try {
            return adoptionCenterService.updateAdoptionCenter(new AdoptionCenterDto(id,name, location));
        } catch (Exception e) {
            throw new CustomException("Could not update adoption center");
        }
    }

    @MutationMapping
    public void deleteAdoptionCenter(@Argument Long id) {
        try {
            adoptionCenterService.deleteAdoptionCenter(id);
        } catch (Exception e) {
            throw new CustomException("Could not delete adoption center");
        }
    }
}
