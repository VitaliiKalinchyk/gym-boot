package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TraineeRepository;
import epam.task.gymboot.repository.TrainerRepository;
import epam.task.gymboot.utils.NameGenerator;
import epam.task.gymboot.utils.PasswordGenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceImplTest {

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private PasswordGenerator passwordGenerator;

    @Mock
    private NameGenerator nameGenerator;

    @InjectMocks
    private TraineeServiceImpl traineeService;

    @Test
    public void testAddTrainee() {
        Trainee trainee = new Trainee();
        User user = new User();
        trainee.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("uniqueUsername");
        when(passwordGenerator.generatePassword()).thenReturn("securePassword");
        when(traineeRepository.add(any(Trainee.class))).thenReturn(Optional.of(trainee));
        when(trainerRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());
        when(traineeRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());

        Optional<Trainee> result = traineeService.add(trainee);

        assertTrue(result.isPresent());
        assertEquals("uniqueUsername", result.get().getUser().getUsername());
        assertEquals("securePassword", result.get().getUser().getPassword());
    }

    @Test
    public void testAddTraineeGenerateUsernameForMultipleCases() {
        Trainee trainee = new Trainee();
        User user = new User();
        trainee.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("username");
        when(traineeRepository.getByUsername("username")).thenReturn(Optional.of(new Trainee()));
        when(nameGenerator.generateUsername(eq("username"), anyList())).thenReturn("username3");

        traineeService.add(trainee);

        assertEquals("username3", trainee.getUser().getUsername());
    }

    @Test
    public void testEditTrainee() {
        Trainee trainee = new Trainee();

        when(traineeRepository.edit(trainee)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.edit(trainee);

        assertTrue(result.isPresent());
        assertEquals(trainee, result.get());
    }

    @Test
    public void testDeleteTrainee() {
        when(traineeRepository.delete(1)).thenReturn(true);

        boolean result = traineeService.delete(1);

        assertTrue(result);
    }

    @Test
    public void testGetById() {
        Trainee trainee = new Trainee();

        when(traineeRepository.getById(1)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.getById(1);

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        Trainee trainee = new Trainee();

        when(traineeRepository.getByUsername("Joe")).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.getByUsername("Joe");

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetTrainees() {
        List<Trainee> trainees = List.of(new Trainee(), new Trainee());

        when(traineeRepository.getTrainees()).thenReturn(trainees);

        List<Trainee> result = traineeService.getTrainees();

        assertEquals(2, result.size());
    }
}