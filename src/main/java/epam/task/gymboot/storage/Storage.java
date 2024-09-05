package epam.task.gymboot.storage;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;

import java.util.Map;

public interface Storage {
    Map<Integer, Trainee> getTrainees();
    void setTrainees(Map<Integer, Trainee> trainees);

    Map<Integer, Trainer> getTrainers();
    void setTrainers(Map<Integer, Trainer> trainers);

    Map<Integer, Training> getTrainings();
    void setTrainings(Map<Integer, Training> trainings);
}