package epam.task.gymboot.facade;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.service.TraineeService;
import epam.task.gymboot.service.TrainerService;
import epam.task.gymboot.service.TrainingService;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record GymBootFacade(TraineeService traineeService,
                            TrainerService trainerService,
                            TrainingService trainingService) {

    public Trainee addTrainee(Trainee trainee) {
        return traineeService.add(trainee)
                .orElseThrow(() -> new RuntimeException("Couldn't add trainee " + trainee));
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerService.add(trainer)
                .orElseThrow(() -> new RuntimeException("Couldn't add trainer " + trainer));
    }

    public Training addTraining(Training training) {
        return trainingService.add(training)
                .orElseThrow(() -> new RuntimeException("Couldn't add training " + training));
    }

    public Trainee editTrainee(Trainee trainee) {
        return traineeService.edit(trainee)
                .orElseThrow(() -> new RuntimeException("Couldn't edit trainee " + trainee));
    }

    public Trainer editTrainer(Trainer trainer) {
        return trainerService.edit(trainer)
                .orElseThrow(() -> new RuntimeException("Couldn't edit trainer " + trainer));
    }

    public String deleteTrainee(int id) {
        return traineeService.delete(id) ? "Trainee with id = " + id + " was deleted" :
                "Couldn't delete trainee with id = " + id;
    }

    public Trainee getTraineeById(int traineeId) {
        return traineeService.getById(traineeId)
                .orElseThrow(() -> new RuntimeException("No trainee with id = " + traineeId));
    }

    public Trainer getTrainerById(int trainerId) {
        return trainerService.getById(trainerId)
                .orElseThrow(() -> new RuntimeException("No trainer with id = " + trainerId));
    }

    public Training getTrainingById(int trainingId) {
        return trainingService.getById(trainingId)
                .orElseThrow(() -> new RuntimeException("No training with id = " + trainingId));
    }

    public Trainee getTraineeByUsername(String username) {
        return traineeService.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("No trainee with username = " + username));
    }

    public Trainer getTrainerByUsername(String username) {
        return trainerService.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("No trainer with username = " + username));
    }

    public List<Trainer> getAllTrainers() {
        return trainerService.getTrainers();
    }

    public List<Trainee> getAllTrainees() {
        return traineeService.getTrainees();
    }

    public List<Training> getAllTrainings() {
        return trainingService.getTrainings();
    }
}