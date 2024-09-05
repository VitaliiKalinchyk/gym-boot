package epam.task.gymboot.entity;

import lombok.Data;

@Data
public class Trainer {
    private int trainerId;
    private User user;
    private TrainingType trainingType;
}