package epam.task.gymboot.storage.initializer;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class YamlDataContainer {
    private final List<Trainee> trainees = new ArrayList<>();
    private final List<Trainer> trainers = new ArrayList<>();
    private final List<Training> trainings = new ArrayList<>();
}