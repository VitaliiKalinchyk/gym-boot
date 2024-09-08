package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TraineeRepository;
import epam.task.gymboot.repository.TrainerRepository;
import epam.task.gymboot.utils.NameGenerator;
import epam.task.gymboot.utils.PasswordGenerator;

import org.junit.jupiter.api.BeforeEach;
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

    private Trainee trainee;
    private User user;


    @BeforeEach
    void setUp() {
        trainee = new Trainee();
        user = new User();
    }

    @Test
    public void testAddTrainee() {
        trainee.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("uniqueUsername");
        when(passwordGenerator.generatePassword()).thenReturn("securePassword");
        when(traineeRepository.add(trainee)).thenReturn(Optional.of(trainee));
        when(trainerRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());
        when(traineeRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());

        Optional<Trainee> result = traineeService.add(trainee);

        assertTrue(result.isPresent());
        assertEquals("uniqueUsername", result.get().getUser().getUsername());
        assertEquals("securePassword", result.get().getUser().getPassword());
    }

    @Test
    public void testAddTraineeGenerateUsernameForMultipleCases() {
        trainee.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("username");
        when(traineeRepository.getByUsername("username")).thenReturn(Optional.of(new Trainee()));
        when(nameGenerator.generateUsername(eq("username"), anyList())).thenReturn("username3");

        traineeService.add(trainee);

        assertEquals("username3", trainee.getUser().getUsername());
    }

    @Test
    public void testAddTraineeNoUser() {
        RuntimeException exception = assertThrows(NullPointerException.class, () -> traineeService.add(trainee));

        assertEquals("Adding trainee failed - user is null", exception.getMessage());
    }

    @Test
    public void testEditTrainee() {
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
        when(traineeRepository.getById(1)).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.getById(1);

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        when(traineeRepository.getByUsername("Joe")).thenReturn(Optional.of(trainee));

        Optional<Trainee> result = traineeService.getByUsername("Joe");

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetTrainees() {
        List<Trainee> trainees = List.of(trainee, trainee);

        when(traineeRepository.getTrainees()).thenReturn(trainees);

        List<Trainee> result = traineeService.getTrainees();

        assertEquals(2, result.size());
    }
}