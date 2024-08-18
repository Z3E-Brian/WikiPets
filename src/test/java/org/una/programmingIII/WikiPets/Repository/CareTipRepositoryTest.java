package org.una.programmingIII.WikiPets.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.una.programmingIII.WikiPets.Model.CareTip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


public class CareTipRepositoryTest {
    CareTip careTip;
    @Mock
    private CareTipRepository careTipRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        careTip = new CareTip();
        careTip.setTitle("Test Title");
        careTip.setContent("Test Content");
        careTipRepository.save(careTip);
    }


    @Test
    void testFindByTitle() {
        when(careTipRepository.findByTitle("Test Title")).thenReturn(careTip);

        CareTip found = careTipRepository.findByTitle("Test Title");

        assertNotNull(found);
        assertEquals("Test Title", found.getTitle());
    }
}
