package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Training;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingRepositoryImplTest {

    @InjectMocks
    private TrainingRepositoryImpl trainingRepository;

    @Mock
    private Map<Integer, Training> trainings;

    @Test
    public void testAddTraining() {
        Training training = new Training();
        training.setTrainingId(1);

        when(trainings.put(1, training)).thenReturn(null);
        when(trainings.get(1)).thenReturn(training);

        Optional<Training> result = trainingRepository.add(training);

        verify(trainings).put(1, training);
        assertTrue(result.isPresent());
        assertEquals(training, result.get());
    }

    @Test
    public void testGetByIdFound() {
        Training training = new Training();
        training.setTrainingId(1);

        when(trainings.get(1)).thenReturn(training);

        Optional<Training> result = trainingRepository.getById(1);

        verify(trainings).get(1);
        assertTrue(result.isPresent());
        assertEquals(training, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainings.get(1)).thenReturn(null);

        Optional<Training> result = trainingRepository.getById(1);

        verify(trainings).get(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainings() {
        Training training1 = new Training();
        training1.setTrainingId(1);
        Training training2 = new Training();
        training2.setTrainingId(2);
        List<Training> trainingList = List.of(training1, training2);

        when(trainings.values()).thenReturn(new HashSet<>(trainingList));

        List<Training> result = trainingRepository.getTrainings();

        verify(trainings).values();
        assertEquals(trainingList, result);
    }

    @Test
    public void testGetNextId() {
        when(trainings.keySet()).thenReturn(Set.of(1, 2, 3));

        int nextId = trainingRepository.getNextId();

        verify(trainings).keySet();
        assertEquals(4, nextId);
    }
}