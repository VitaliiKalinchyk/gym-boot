package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainee;
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
public class TraineeRepositoryImplTest {

    @InjectMocks
    private TraineeRepositoryImpl traineeRepository;

    @Mock
    private Map<Integer, Trainee> trainees;

    @Test
    public void testAddTrainee() {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        when(trainees.put(1, trainee)).thenReturn(null);
        when(trainees.get(1)).thenReturn(trainee);

        Optional<Trainee> result = traineeRepository.add(trainee);

        verify(trainees).put(1, trainee);
        verify(trainees).get(1);
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    public void testEditTrainee() {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        when(trainees.replace(1, trainee)).thenReturn(trainee);

        Optional<Trainee> result = traineeRepository.edit(trainee);

        verify(trainees).replace(1, trainee);
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    public void testDeleteTrainee() {
        when(trainees.remove(1)).thenReturn(new Trainee());

        boolean result = traineeRepository.delete(1);

        verify(trainees).remove(1);
        assertTrue(result);
    }

    @Test
    public void testDeleteTraineeNotFound() {
        when(trainees.remove(1)).thenReturn(null);

        boolean result = traineeRepository.delete(1);

        verify(trainees).remove(1);
        assertFalse(result);
    }

    @Test
    public void testGetByIdFound() {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);

        when(trainees.get(1)).thenReturn(trainee);

        Optional<Trainee> result = traineeRepository.getById(1);

        verify(trainees).get(1);
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    public void testGetByIdNotFound() {
        when(trainees.get(1)).thenReturn(null);

        Optional<Trainee> result = traineeRepository.getById(1);

        verify(trainees).get(1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        Trainee trainee = new Trainee();
        User user = new User();
        user.setUsername("Karl");
        trainee.setUser(user);

        when(trainees.values()).thenReturn(Collections.singleton(trainee));

        Optional<Trainee> result = traineeRepository.getByUsername("Karl");

        verify(trainees).values();
        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    public void testGetByUsernameNotFound() {
        when(trainees.values()).thenReturn(Collections.emptySet());

        Optional<Trainee> result = traineeRepository.getByUsername("Karl");

        verify(trainees).values();
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetTrainees() {
        Trainee trainee1 = new Trainee();
        trainee1.setTraineeId(1);
        Trainee trainee2 = new Trainee();
        trainee2.setTraineeId(2);
        List<Trainee> traineeList = List.of(trainee1, trainee2);

        when(trainees.values()).thenReturn(new HashSet<>(traineeList));

        List<Trainee> result = traineeRepository.getTrainees();

        verify(trainees).values();
        assertEquals(traineeList, result);
    }

    @Test
    public void testGetAllTraineeUsernamesByUsername() {
        Trainee trainee1 = new Trainee();
        trainee1.setTraineeId(1);
        User user1 = new User();
        user1.setUsername("Joe.Doe");
        trainee1.setUser(user1);

        Trainee trainee2 = new Trainee();
        trainee2.setTraineeId(2);
        User user2 = new User();
        user2.setUsername("Joe.Doe1");
        trainee2.setUser(user2);

        Trainee trainee3 = new Trainee();
        trainee3.setTraineeId(3);
        User user3 = new User();
        user3.setUsername("Jane.Doe");
        trainee3.setUser(user3);

        when(trainees.values()).thenReturn(Set.of(trainee1, trainee2));

        List<String> result = traineeRepository.getAllTraineeUsernamesByUsername("Joe.Doe");

        verify(trainees).values();

        List<String> expectedUsernames = List.of("Joe.Doe", "Joe.Doe1");

        assertEquals(2, expectedUsernames.size());
        assertTrue(result.containsAll(expectedUsernames) && expectedUsernames.containsAll(result),
                "The result list does not match the expected list");
    }

    @Test
    public void testGetNextId() {
        when(trainees.keySet()).thenReturn(Set.of(1, 2, 3));

        int nextId = traineeRepository.getNextId();

        verify(trainees).keySet();
        assertEquals(4, nextId);
    }
}