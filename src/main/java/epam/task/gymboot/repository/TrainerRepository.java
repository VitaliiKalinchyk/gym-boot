package epam.task.gymboot.repository;

import epam.task.gymboot.entity.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository {

    Optional<Trainer> add(Trainer trainee);

    Optional<Trainer> edit(Trainer trainer);

    Optional<Trainer> getById(int trainerId);

    Optional<Trainer> getByUsername(String username);

    List<Trainer> getTrainers();

    List<String> getAllTrainerUsernamesByUsername(String username);

    int getNextId();
}