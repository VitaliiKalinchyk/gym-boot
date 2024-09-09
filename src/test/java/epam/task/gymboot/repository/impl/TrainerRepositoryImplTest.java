package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.User;

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
public class TrainerRepositoryImplTest {

    @InjectMocks
    private TrainerRepositoryImpl trainerRepository;

    @Mock
    private Map<Integer, Trainer> trainers;

    private Trainer trainer1;

    @BeforeEach
    void setUp() {
        trainer1 = new Trainer();
    }

    @Test
    public void testAddTrainerNoTrainers() {
        when(trainers.keySet()).thenReturn(Collections.emptySet());
        when(trainers.get(1)).thenReturn(trainer1);

        Optional<Trainer> result = trainerRepository.add(trainer1);

        assertTrue(result.isPresent());
        assertEquals(trainer1, result.get());
    }

    @Test
    public void testAddTrainerFewTrainers() {
        when(trainers.keySet()).thenReturn(Set.of(2, 5, 9));
        when(trainers.get(10)).thenReturn(trainer1);

        Optional<Trainer> result = trainerRepository.add(trainer1);

        assertTrue(result.isPresent());
        assertEquals(trainer1, result.get());
    }

    @Test
    public void testEditTrainer() {
        trainer1.setTrainerId(1);

        when(trainers.replace(1, trainer1)).thenReturn(trainer1);

        Optional<Trainer> result = trainerRepository.edit(trainer1);

        assertTrue(result.isPresent());
        assertEquals(trainer1, result.get());
    }

    @Test
    public void testGetByIdFound() {
        trainer1.setTrainerId(1);

        when(trainers.get(1)).thenReturn(trainer1);

        Optional<Trainer> result = trainerRepository.getById(1);

        assertTrue(result.isPresent());
        assertEquals(trainer1, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        Optional<Trainer> result = trainerRepository.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        User user = new User();
        user.setUsername("Karl");
        trainer1.setUser(user);

        when(trainers.values()).thenReturn(Collections.singleton(trainer1));

        Optional<Trainer> result = trainerRepository.getByUsername("Karl");

        assertTrue(result.isPresent());
        assertEquals(trainer1, result.get());
    }

    @Test
    public void testGetByUsernameNotFound() {
        when(trainers.values()).thenReturn(Collections.emptySet());

        Optional<Trainer> result = trainerRepository.getByUsername("Karl");

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainers() {
        trainer1.setTrainerId(1);
        Trainer trainer2 = new Trainer();
        trainer2.setTrainerId(2);
        List<Trainer> trainerList = List.of(trainer1, trainer2);

        when(trainers.values()).thenReturn(new HashSet<>(trainerList));

        List<Trainer> result = trainerRepository.getTrainers();

        assertEquals(trainerList, result);
    }

    @Test
    public void testGetAllTrainerUsernamesByUsername() {
        trainer1.setTrainerId(1);
        User user1 = new User();
        user1.setUsername("Joe.Doe");
        trainer1.setUser(user1);

        Trainer trainer2 = new Trainer();
        trainer2.setTrainerId(2);
        User user2 = new User();
        user2.setUsername("Joe.Doe1");
        trainer2.setUser(user2);

        List<String> expectedUsernames = List.of("Joe.Doe", "Joe.Doe1");

        when(trainers.values()).thenReturn(Set.of(trainer1, trainer2));

        List<String> result = trainerRepository.getAllTrainerUsernamesByUsername("Joe.Doe");

        assertEquals(2, expectedUsernames.size());
        assertTrue(result.containsAll(expectedUsernames) && expectedUsernames.containsAll(result),
                "The result list does not match the expected list");
    }
}