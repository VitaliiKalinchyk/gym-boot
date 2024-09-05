package epam.task.gymboot.repository;

import epam.task.gymboot.entity.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository {
    Optional<Training> add(Training training);

    Optional<Training> getById(int trainingId);

    List<Training> getTrainings();

    int getNextId();
}