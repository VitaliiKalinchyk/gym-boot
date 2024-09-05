package epam.task.gymboot.repository;

import epam.task.gymboot.entity.Trainee;

import java.util.List;
import java.util.Optional;

public interface TraineeRepository {

    Optional<Trainee> add(Trainee trainee);

    Optional<Trainee> edit(Trainee trainee);

    boolean delete(int traineeId);

    Optional<Trainee> getById(int traineeId);

    Optional<Trainee> getByUsername(String username);

    List<Trainee> getTrainees();

    List<String> getAllTraineeUsernamesByUsername(String username);

    int getNextId();
}