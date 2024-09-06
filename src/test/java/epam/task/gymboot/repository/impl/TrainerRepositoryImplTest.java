package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.User;
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
public class TrainerRepositoryImplTest {

    @InjectMocks
    private TrainerRepositoryImpl trainerRepository;

    @Mock
    private Map<Integer, Trainer> trainers;

    @Test
    public void testAddTrainer() {
        Trainer trainer = new Trainer();

        when(trainers.put(1, trainer)).thenReturn(null);
        when(trainers.get(1)).thenReturn(trainer);

        Optional<Trainer> result = trainerRepository.add(trainer);

        verify(trainers).put(1, trainer);
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    public void testEditTrainer() {
        Trainer trainer = new Trainer();
        trainer.setTrainerId(1);

        when(trainers.replace(1, trainer)).thenReturn(trainer);

        Optional<Trainer> result = trainerRepository.edit(trainer);

        verify(trainers).replace(1, trainer);
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    public void testGetByIdFound() {
        Trainer trainer = new Trainer();
        trainer.setTrainerId(1);

        when(trainers.get(1)).thenReturn(trainer);

        Optional<Trainer> result = trainerRepository.getById(1);

        verify(trainers).get(1);
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainers.get(1)).thenReturn(null);

        Optional<Trainer> result = trainerRepository.getById(1);

        verify(trainers).get(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        Trainer trainer = new Trainer();
        User user = new User();
        user.setUsername("Karl");
        trainer.setUser(user);

        when(trainers.values()).thenReturn(Collections.singleton(trainer));

        Optional<Trainer> result = trainerRepository.getByUsername("Karl");

        verify(trainers).values();
        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    public void testGetByUsernameNotFound() {
        when(trainers.values()).thenReturn(Collections.emptySet());

        Optional<Trainer> result = trainerRepository.getByUsername("Karl");

        verify(trainers).values();
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainers() {
        Trainer trainer1 = new Trainer();
        trainer1.setTrainerId(1);
        Trainer trainer2 = new Trainer();
        trainer2.setTrainerId(2);
        List<Trainer> trainerList = List.of(trainer1, trainer2);

        when(trainers.values()).thenReturn(new HashSet<>(trainerList));

        List<Trainer> result = trainerRepository.getTrainers();

        verify(trainers).values();
        assertEquals(trainerList, result);
    }

    @Test
    public void testGetAllTrainerUsernamesByUsername() {
        Trainer trainer1 = new Trainer();
        trainer1.setTrainerId(1);
        User user1 = new User();
        user1.setUsername("Joe.Doe");
        trainer1.setUser(user1);

        Trainer trainer2 = new Trainer();
        trainer2.setTrainerId(2);
        User user2 = new User();
        user2.setUsername("Joe.Doe1");
        trainer2.setUser(user2);

        Trainer trainer3 = new Trainer();
        trainer3.setTrainerId(3);
        User user3 = new User();
        user3.setUsername("Jane.Doe");
        trainer3.setUser(user3);

        when(trainers.values()).thenReturn(Set.of(trainer1, trainer2));

        List<String> result = trainerRepository.getAllTrainerUsernamesByUsername("Joe.Doe");

        verify(trainers).values();

        List<String> expectedUsernames = List.of("Joe.Doe", "Joe.Doe1");

        assertEquals(2, expectedUsernames.size());
        assertTrue(result.containsAll(expectedUsernames) && expectedUsernames.containsAll(result),
                "The result list does not match the expected list");
    }
}