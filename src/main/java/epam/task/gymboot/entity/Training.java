package epam.task.gymboot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode(of = "trainingId", callSuper = false)
@ToString
public class Training {
    private int trainingId;
    private int traineeId;
    private int trainerId;
    private String name;
    private TrainingType trainingType;
    private LocalDate date;
    private int durationMinutes;
}