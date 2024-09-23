//package org.una.programmingIII.WikiPets.Service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapper;
//import org.una.programmingIII.WikiPets.Mapper.GenericMapperFactory;
//import org.una.programmingIII.WikiPets.Model.HealthIssue;
//import org.una.programmingIII.WikiPets.Dto.HealthIssueDto;
//import org.una.programmingIII.WikiPets.Repository.HealthIssueRepository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class HealthIssueServiceImplementationTest {
//    @Mock
//    private HealthIssueRepository healthIssueRepository;
//
//    @Mock
//    private GenericMapperFactory mapperFactory;
//
//    @Mock
//    private GenericMapper<HealthIssue,HealthIssueDto> healthIssueMapper;
//
//    @InjectMocks
//    private HealthIssueServiceImplementation healthIssueServiceImplementation;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        when(mapperFactory.createMapper(HealthIssue.class, HealthIssueDto.class)).thenReturn(healthIssueMapper);
//     //   healthIssueServiceImplementation = new HealthIssueServiceImplementation(healthIssueRepository, mapperFactory);
//    }
//
//    @Test
//    public void getHealthIssueByIdTest() {
//        HealthIssue healthIssue = new HealthIssue();
//        when(healthIssueRepository.findById(anyLong())).thenReturn(Optional.of(healthIssue));
//        when(healthIssueMapper.convertToDTO(any(HealthIssue.class))).thenReturn(new HealthIssueDto());
//
//        HealthIssueDto healthIssueDto = healthIssueServiceImplementation.getHealthIssueById(1L);
//
//        assertNotNull(healthIssueDto);
//        verify(healthIssueRepository, times(1)).findById(anyLong());
//    }
//    @Test
//    public void createHealthIssueTest() {
//        HealthIssue healthIssue = new HealthIssue();
//
//        when(healthIssueRepository.save(any(HealthIssue.class))).thenReturn(healthIssue);
//        when(healthIssueMapper.convertToDTO(any(HealthIssue.class))).thenReturn(new HealthIssueDto());
//        when(healthIssueMapper.convertToEntity(any(HealthIssueDto.class))).thenReturn(healthIssue);
//
//        HealthIssueDto healthIssueDto = healthIssueServiceImplementation.createHealthIssue(new HealthIssueDto());
//
//        assertNotNull(healthIssueDto);
//        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
//    }
//
//    @Test
//    public void updateHealthIssueTest() {
//        HealthIssue healthIssue = new HealthIssue();
//
//        when(healthIssueRepository.save(any(HealthIssue.class))).thenReturn(healthIssue);
//        when(healthIssueMapper.convertToDTO(any(HealthIssue.class))).thenReturn(new HealthIssueDto());
//        when(healthIssueMapper.convertToEntity(any(HealthIssueDto.class))).thenReturn(healthIssue);
//
//        HealthIssueDto healthIssueDto = healthIssueServiceImplementation.updateHealthIssue(new HealthIssueDto());
//
//        assertNotNull(healthIssueDto);
//        verify(healthIssueRepository, times(1)).save(any(HealthIssue.class));
//    }
//    @Test
//    public void deleteHealthIssueTest() {
//
//        healthIssueServiceImplementation.deleteHealthIssue(1L);
//
//        verify(healthIssueRepository, times(1)).deleteById(1L);
//    }
//}
