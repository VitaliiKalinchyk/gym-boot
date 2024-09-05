package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TraineeRepository;
import epam.task.gymboot.repository.TrainerRepository;
import epam.task.gymboot.service.TrainerService;
import epam.task.gymboot.utils.NameGenerator;
import epam.task.gymboot.utils.PasswordGenerator;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    private final TraineeRepository traineeRepository;

    private final PasswordGenerator passwordGenerator;

    private final NameGenerator nameGenerator;

    @Override
    public Optional<Trainer> add(Trainer trainer) {
        int nextId = trainerRepository.getNextId();

        trainer.setTrainerId(nextId);
        trainer.getUser().setUsername(generateUsername(trainer));
        trainer.getUser().setPassword(passwordGenerator.generatePassword());

        return trainerRepository.add(trainer);
    }

    @Override
    public Optional<Trainer> edit(Trainer trainer) {
        return trainerRepository.edit(trainer);
    }

    @Override
    public Optional<Trainer> getById(int trainerId) {
        return trainerRepository.getById(trainerId);
    }

    @Override
    public Optional<Trainer> getByUsername(String username) {
        return trainerRepository.getByUsername(username);
    }

    @Override
    public List<Trainer> getTrainers() {
        return trainerRepository.getTrainers();
    }

    private String generateUsername(Trainer trainer) {
        User user = trainer.getUser();
        String username = nameGenerator.generateUsername(user);

        if (traineeRepository.getByUsername(username).isEmpty() &&
                trainerRepository.getByUsername(username).isEmpty()) {
            return username;
        }

        return generateUsernameForMultipleCases(username, user);
    }

    private String generateUsernameForMultipleCases(String username, User user){
        List<String> usernames = Stream.concat(traineeRepository.getAllTraineeUsernamesByUsername(username).stream(),
                                               trainerRepository.getAllTrainerUsernamesByUsername(username).stream())
                                       .toList();

        return nameGenerator.generateUsername(user, usernames);
    }
}