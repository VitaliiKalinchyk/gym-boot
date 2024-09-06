package epam.task.gymboot.facade;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.service.TraineeService;
import epam.task.gymboot.service.TrainerService;
import epam.task.gymboot.service.TrainingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GymBootFacadeTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private GymBootFacade gymBootFacade;

    private Trainee trainee;
    private Trainer trainer;
    private Training training;

    @BeforeEach
    void setUp() {
        trainee = new Trainee();
        trainer = new Trainer();
        training = new Training();
    }

    @Test
    void testAddTrainee() {
        when(traineeService.add(trainee)).thenReturn(Optional.of(trainee));

        Trainee result = gymBootFacade.addTrainee(trainee);

        assertNotNull(result);
        assertEquals(trainee, result);
    }

    @Test
    void testAddTraineeThrowsException() {
        when(traineeService.add(trainee)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.addTrainee(trainee));

        assertEquals("Couldn't add trainee " + trainee, exception.getMessage());
    }

    @Test
    void testAddTrainer() {
        when(trainerService.add(trainer)).thenReturn(Optional.of(trainer));

        Trainer result = gymBootFacade.addTrainer(trainer);

        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test
    void testAddTrainerThrowsException() {
        when(trainerService.add(trainer)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.addTrainer(trainer));

        assertEquals("Couldn't add trainer " + trainer, exception.getMessage());
    }

    @Test
    void testAddTraining() {
        when(trainingService.add(training)).thenReturn(Optional.of(training));

        Training result = gymBootFacade.addTraining(training);

        assertNotNull(result);
        assertEquals(training, result);
    }

    @Test
    void testAddTrainingThrowsException() {
        when(trainingService.add(training)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.addTraining(training));

        assertEquals("Couldn't add training " + training, exception.getMessage());
    }

    @Test
    void testEditTrainee() {
        when(traineeService.edit(trainee)).thenReturn(Optional.of(trainee));

        Trainee result = gymBootFacade.editTrainee(trainee);

        assertNotNull(result);
        assertEquals(trainee, result);
    }

    @Test
    void testEditTraineeThrowsException() {
        when(traineeService.edit(trainee)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.editTrainee(trainee));

        assertEquals("Couldn't edit trainee " + trainee, exception.getMessage());
    }

    @Test
    void testEditTrainer() {
        when(trainerService.edit(trainer)).thenReturn(Optional.of(trainer));

        Trainer result = gymBootFacade.editTrainer(trainer);

        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test
    void testEditTrainerThrowsException() {
        when(trainerService.edit(trainer)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.editTrainer(trainer));

        assertEquals("Couldn't edit trainer " + trainer, exception.getMessage());
    }

    @Test
    void testDeleteTrainee() {
        when(traineeService.delete(1)).thenReturn(true);

        String result = gymBootFacade.deleteTrainee(1);

        assertEquals("Trainee with id = 1 was deleted", result);
    }

    @Test
    void testDeleteTraineeFalse() {
        when(traineeService.delete(1)).thenReturn(false);

        String result = gymBootFacade.deleteTrainee(1);

        assertEquals("Couldn't delete trainee with id = 1", result);
    }

    @Test
    void testGetTraineeById() {
        when(traineeService.getById(1)).thenReturn(Optional.of(trainee));

        Trainee result = gymBootFacade.getTraineeById(1);

        assertNotNull(result);
        assertEquals(trainee, result);
    }

    @Test
    void testGetTraineeByIdThrowsException() {
        when(traineeService.getById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.getTraineeById(1));

        assertEquals("No trainee with id = 1", exception.getMessage());
    }

    @Test
    void testGetTrainerById() {
        when(trainerService.getById(1)).thenReturn(Optional.of(trainer));

        Trainer result = gymBootFacade.getTrainerById(1);

        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test
    void testGetTrainerByIdThrowsException() {
        when(trainerService.getById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.getTrainerById(1));

        assertEquals("No trainer with id = 1", exception.getMessage());
    }

    @Test
    void testGetTrainingById() {
        when(trainingService.getById(1)).thenReturn(Optional.of(training));

        Training result = gymBootFacade.getTrainingById(1);

        assertNotNull(result);
        assertEquals(training, result);
    }

    @Test
    void testGetTrainingByIdThrowsException() {
        when(trainingService.getById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.getTrainingById(1));

        assertEquals("No training with id = 1", exception.getMessage());
    }

    @Test
    void testGetTraineeByUsername() {
        when(traineeService.getByUsername("username")).thenReturn(Optional.of(trainee));

        Trainee result = gymBootFacade.getTraineeByUsername("username");

        assertNotNull(result);
        assertEquals(trainee, result);
    }

    @Test
    void testGetTraineeByUsernameThrowException() {
        when(traineeService.getByUsername("username")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.getTraineeByUsername("username"));

        assertEquals("No trainee with username = username", exception.getMessage());
    }

    @Test
    void testGetTrainerByUsername() {
        when(trainerService.getByUsername("username")).thenReturn(Optional.of(trainer));

        Trainer result = gymBootFacade.getTrainerByUsername("username");

        assertNotNull(result);
        assertEquals(trainer, result);
    }

    @Test
    void testGetTrainerByUsernameThrowException() {
        when(trainerService.getByUsername("username")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> gymBootFacade.getTrainerByUsername("username"));

        assertEquals("No trainer with username = username", exception.getMessage());
    }

    @Test
    void testGetAllTrainees() {
        List<Trainee> trainees = List.of(trainee);
        when(traineeService.getTrainees()).thenReturn(trainees);

        List<Trainee> result = gymBootFacade.getAllTrainees();

        assertEquals(trainees, result);
    }

    @Test
    void testGetAllTrainers() {
        List<Trainer> trainers = List.of(trainer);
        when(trainerService.getTrainers()).thenReturn(trainers);

        List<Trainer> result = gymBootFacade.getAllTrainers();

        assertEquals(trainers, result);
    }

    @Test
    void testGetAllTrainings() {
        List<Training> trainings = List.of(training);
        when(trainingService.getTrainings()).thenReturn(trainings);

        List<Training> result = gymBootFacade.getAllTrainings();

        assertEquals(trainings, result);
    }
}