package epam.task.gymboot.storage;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;

import java.util.Map;

public interface Storage {
    Map<Integer, Trainee> getTrainees();

    Map<Integer, Trainer> getTrainers();

    Map<Integer, Training> getTrainings();
}