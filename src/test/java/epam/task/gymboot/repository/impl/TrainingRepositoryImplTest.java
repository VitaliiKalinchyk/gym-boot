package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingRepositoryImplTest {

    @InjectMocks
    private TrainingRepositoryImpl trainingRepository;

    @Mock
    private Map<Integer, Training> trainings;

    private Training training1;

    @BeforeEach
    void setUp() {
        training1 = new Training();
    }

    @Test
    public void testAddTrainingNoTrainings() {
        when(trainings.keySet()).thenReturn(Collections.emptySet());
        when(trainings.get(1)).thenReturn(training1);

        Optional<Training> result = trainingRepository.add(training1);

        assertTrue(result.isPresent());
        assertEquals(training1, result.get());
    }

    @Test
    public void testAddTrainingFewTrainings() {
        when(trainings.keySet()).thenReturn(Set.of(2, 5, 9));
        when(trainings.get(10)).thenReturn(training1);

        Optional<Training> result = trainingRepository.add(training1);

        assertTrue(result.isPresent());
        assertEquals(training1, result.get());
    }

    @Test
    public void testGetByIdFound() {
        training1.setTrainingId(1);

        when(trainings.get(1)).thenReturn(training1);

        Optional<Training> result = trainingRepository.getById(1);

        assertTrue(result.isPresent());
        assertEquals(training1, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        Optional<Training> result = trainingRepository.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainings() {
        training1.setTrainingId(1);
        Training training2 = new Training();
        training2.setTrainingId(2);
        List<Training> trainingList = List.of(training1, training2);

        when(trainings.values()).thenReturn(new HashSet<>(trainingList));

        List<Training> result = trainingRepository.getTrainings();

        assertEquals(trainingList, result);
    }
}