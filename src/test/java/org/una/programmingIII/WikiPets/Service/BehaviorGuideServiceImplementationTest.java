package org.una.programmingIII.WikiPets.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.BehaviorGuideDto;
import org.una.programmingIII.WikiPets.Mapper.BehaviorGuideMapper;
import org.una.programmingIII.WikiPets.Model.BehaviorGuide;
import org.una.programmingIII.WikiPets.Repository.BehaviorGuideRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BehaviorGuideServiceImplementationTest {

    @Mock
    private BehaviorGuideRepository behaviorGuideRepository;

    @Mock
    private BehaviorGuideMapper behaviorGuideMapper;

    @InjectMocks
    private BehaviorGuideServiceImplementation behaviorGuideServiceImplementation;

    private BehaviorGuide behaviorGuide;
    private BehaviorGuideDto behaviorGuideDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        behaviorGuide = new BehaviorGuide();
        behaviorGuide.setId(1L);
        behaviorGuide.setTitle("Training Guide");

        behaviorGuideDto = new BehaviorGuideDto();
        behaviorGuideDto.setId(1L);
        behaviorGuideDto.setTitle("Training Guide");
    }

    @Test
    public void testGetBehaviorGuideById() {
        when(behaviorGuideRepository.findById(1L)).thenReturn(Optional.of(behaviorGuide));
        when(behaviorGuideMapper.BehaviorGuideDto(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto dto = behaviorGuideServiceImplementation.getBehaviorGuideById(1L);

        assertNotNull(dto);
        assertEquals("Training Guide", dto.getTitle());
        verify(behaviorGuideRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveBehaviorGuide() {
        when(behaviorGuideRepository.save(any(BehaviorGuide.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.toBehaviorGuide(any(BehaviorGuideDto.class))).thenReturn(behaviorGuide);
        when(behaviorGuideMapper.BehaviorGuideDto(any(BehaviorGuide.class))).thenReturn(behaviorGuideDto);

        BehaviorGuideDto savedDto = behaviorGuideServiceImplementation.saveBehaviorGuide(behaviorGuideDto);

        assertNotNull(savedDto);
        assertEquals("Training Guide", savedDto.getTitle());
        verify(behaviorGuideRepository, times(1)).save(any(BehaviorGuide.class));
    }

    @Test
    public void testDeleteBehaviorGuide() {
        behaviorGuideServiceImplementation.deleteBehaviorGuide(1L);

        verify(behaviorGuideRepository, times(1)).deleteById(1L);
    }
}
