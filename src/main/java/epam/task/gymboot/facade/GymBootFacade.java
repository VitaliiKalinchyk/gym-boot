package epam.task.gymboot.facade;

import epam.task.gymboot.service.TraineeService;
import epam.task.gymboot.service.TrainerService;
import epam.task.gymboot.service.TrainingService;

import org.springframework.stereotype.Component;

@Component
public record GymBootFacade(TraineeService traineeService,
                            TrainerService trainerService,
                            TrainingService trainingService) {
}