package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Training;
import epam.task.gymboot.repository.TrainingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private Training training;

    @BeforeEach
    void setUp() {
        training = new Training();
        training.setTrainingId(1);
    }

    @Test
    void addTraining() {
        when(trainingRepository.add(training)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingService.add(training);

        assertTrue(result.isPresent());
    }

    @Test
    void getTrainingById() {
        when(trainingRepository.getById(1)).thenReturn(Optional.of(training));

        Optional<Training> result = trainingService.getById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getTrainingId());
    }

    @Test
    void getTrainings() {
        List<Training> trainings = List.of(training, training);

        when(trainingRepository.getTrainings()).thenReturn(trainings);

        List<Training> result = trainingService.getTrainings();

        assertEquals(trainings.size(), result.size());
    }
}