package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Trainer;
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
class TrainerServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;

    @Mock
    private TraineeRepository traineeRepository;

    @Mock
    private PasswordGenerator passwordGenerator;

    @Mock
    private NameGenerator nameGenerator;

    @InjectMocks
    private TrainerServiceImpl trainerService;

    private Trainer trainer;
    private User user;

    @BeforeEach
    void setUp() {
        trainer = new Trainer();
        user =  new User();
    }

    @Test
    public void testAddTrainer() {
        trainer.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("uniqueUsername");
        when(passwordGenerator.generatePassword()).thenReturn("securePassword");
        when(trainerRepository.add(trainer)).thenReturn(Optional.of(trainer));
        when(trainerRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());
        when(traineeRepository.getByUsername("uniqueUsername")).thenReturn(Optional.empty());

        Optional<Trainer> result = trainerService.add(trainer);

        assertTrue(result.isPresent());
        assertEquals("uniqueUsername", trainer.getUser().getUsername());
        assertEquals("securePassword", trainer.getUser().getPassword());
    }

    @Test
    public void testGenerateUsernameForMultipleCases() {
        trainer.setUser(user);

        when(nameGenerator.generateUsername(user)).thenReturn("username");
        when(trainerRepository.getByUsername("username")).thenReturn(Optional.of(new Trainer()));
        when(nameGenerator.generateUsername(eq("username"), anyList())).thenReturn("username3");

        trainerService.add(trainer);

        assertEquals("username3", trainer.getUser().getUsername());
    }

    @Test
    public void testAddTrainerNoUser() {
        RuntimeException exception = assertThrows(NullPointerException.class, () -> trainerService.add(trainer));

        assertEquals("Adding trainer failed - user is null", exception.getMessage());
    }

    @Test
    public void testEditTrainer() {
        when(trainerRepository.edit(trainer)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.edit(trainer);

        assertTrue(result.isPresent());
        assertEquals(trainer, result.get());
    }

    @Test
    public void testGetById() {
        when(trainerRepository.getById(1)).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.getById(1);

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetByUsername() {
        when(trainerRepository.getByUsername("Joe")).thenReturn(Optional.of(trainer));

        Optional<Trainer> result = trainerService.getByUsername("Joe");

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetTrainers() {
        List<Trainer> trainers = List.of(trainer, trainer);

        when(trainerRepository.getTrainers()).thenReturn(trainers);

        List<Trainer> result = trainerService.getTrainers();

        assertEquals(2, result.size());
    }
}