package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainee;
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
public class TraineeRepositoryImplTest {

    @InjectMocks
    private TraineeRepositoryImpl traineeRepository;

    @Mock
    private Map<Integer, Trainee> trainees;

    private Trainee trainee1;

    @BeforeEach
    void setUp() {
        trainee1 = new Trainee();
    }

    @Test
    public void testAddTraineeNoTrainees() {
        when(trainees.keySet()).thenReturn(Collections.emptySet());
        when(trainees.get(1)).thenReturn(trainee1);

        Optional<Trainee> result = traineeRepository.add(trainee1);

        assertTrue(result.isPresent());
        assertEquals(trainee1, result.get());
    }

    @Test
    public void testAddTraineeFewTrainees() {
        when(trainees.keySet()).thenReturn(Set.of(2, 5, 9));
        when(trainees.get(10)).thenReturn(trainee1);

        Optional<Trainee> result = traineeRepository.add(trainee1);

        assertTrue(result.isPresent());
        assertEquals(trainee1, result.get());
    }

    @Test
    public void testEditTrainee() {
        trainee1.setTraineeId(1);

        when(trainees.replace(1, trainee1)).thenReturn(trainee1);

        Optional<Trainee> result = traineeRepository.edit(trainee1);

        assertTrue(result.isPresent());
        assertEquals(trainee1, result.get());
    }

    @Test
    public void testDeleteTrainee() {
        when(trainees.remove(1)).thenReturn(new Trainee());

        boolean result = traineeRepository.delete(1);

        assertTrue(result);
    }

    @Test
    public void testDeleteTraineeNotFound() {
        when(trainees.remove(1)).thenReturn(null);

        boolean result = traineeRepository.delete(1);

        assertFalse(result);
    }

    @Test
    public void testGetByIdFound() {
        trainee1.setTraineeId(1);

        when(trainees.get(1)).thenReturn(trainee1);

        Optional<Trainee> result = traineeRepository.getById(1);

        assertTrue(result.isPresent());
        assertEquals(trainee1, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainees.get(1)).thenReturn(null);

        Optional<Trainee> result = traineeRepository.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        User user = new User();
        user.setUsername("Karl");
        trainee1.setUser(user);

        when(trainees.values()).thenReturn(Collections.singleton(trainee1));

        Optional<Trainee> result = traineeRepository.getByUsername("Karl");

        assertTrue(result.isPresent());
        assertEquals(trainee1, result.get());
    }

    @Test
    public void testGetByUsernameNotFound() {
        when(trainees.values()).thenReturn(Collections.emptySet());

        Optional<Trainee> result = traineeRepository.getByUsername("Karl");

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainees() {
        trainee1.setTraineeId(1);
        Trainee trainee2 = new Trainee();
        trainee2.setTraineeId(2);
        List<Trainee> traineeList = List.of(trainee1, trainee2);

        when(trainees.values()).thenReturn(new HashSet<>(traineeList));

        List<Trainee> result = traineeRepository.getTrainees();

        assertEquals(traineeList, result);
    }

    @Test
    public void testGetAllTraineeUsernamesByUsername() {
        trainee1.setTraineeId(1);
        User user1 = new User();
        user1.setUsername("Joe.Doe");
        trainee1.setUser(user1);

        Trainee trainee2 = new Trainee();
        trainee2.setTraineeId(2);
        User user2 = new User();
        user2.setUsername("Joe.Doe1");
        trainee2.setUser(user2);

        List<String> expectedUsernames = List.of("Joe.Doe", "Joe.Doe1");

        when(trainees.values()).thenReturn(Set.of(trainee1, trainee2));

        List<String> result = traineeRepository.getAllTraineeUsernamesByUsername("Joe.Doe");

        assertEquals(2, expectedUsernames.size());
        assertTrue(result.containsAll(expectedUsernames) && expectedUsernames.containsAll(result),
                "The result list does not match the expected list");
    }
}