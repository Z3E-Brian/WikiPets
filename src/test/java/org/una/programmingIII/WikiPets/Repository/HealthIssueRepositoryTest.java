package org.una.programmingIII.WikiPets.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.una.programmingIII.WikiPets.Model.HealthIssue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HealthIssueRepositoryTest {
    @Mock
    private HealthIssueRepository healthIssueRepository;

    private HealthIssue healthIssue;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        healthIssue = new HealthIssue();
        healthIssue.setId(1L);
        healthIssue.setName("Feline Leukemia Virus");
        healthIssue.setDescription("Feline leukemia virus (FeLV) is second only to trauma as the leading cause of death in cats, killing 85% of persistently infected felines within three years of diagnosis. The virus commonly causes anemia or lymphoma, but because it suppresses the immune system, it can also predispose cats to deadly infections. The virus is shed in saliva, nasal secretions, urine, feces, and milk of infected cats. It is usually transmitted through casual contact, mutual grooming and the sharing of litter boxes and food and water dishes. Kittens can contract the disease in utero or through an infected mother's milk.");
    }
    @Test
    public void findByHealthIssueNameTest() {
        when(healthIssueRepository.findByHealthIssueName("Feline Leukemia Virus")).thenReturn(healthIssue);
        HealthIssue result = healthIssueRepository.findByHealthIssueName("Feline Leukemia Virus");

        assertEquals(1L, result.getId());
        assertEquals("Feline Leukemia Virus", result.getName());
    }

}
