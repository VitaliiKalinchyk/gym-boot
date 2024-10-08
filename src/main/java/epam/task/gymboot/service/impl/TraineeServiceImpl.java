package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TraineeRepository;
import epam.task.gymboot.repository.TrainerRepository;
import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.utils.NameGenerator;
import epam.task.gymboot.utils.PasswordGenerator;
import epam.task.gymboot.service.TraineeService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;

    private final TrainerRepository trainerRepository;

    private final PasswordGenerator passwordGenerator;

    private final NameGenerator nameGenerator;

    @Override
    public Optional<Trainee> add(Trainee trainee) {
        User user = Objects.requireNonNull(trainee.getUser(), "Adding trainee failed - user is null");

        user.setUsername(generateUsername(user));
        user.setPassword(passwordGenerator.generatePassword());

        return traineeRepository.add(trainee);
    }

    @Override
    public Optional<Trainee> edit(Trainee trainee) {
        return traineeRepository.edit(trainee);
    }

    @Override
    public boolean delete(int traineeId) {
        return traineeRepository.delete(traineeId);
    }

    @Override
    public Optional<Trainee> getById(int traineeId) {
        return traineeRepository.getById(traineeId);
    }

    @Override
    public Optional<Trainee> getByUsername(String username) {
        return traineeRepository.getByUsername(username);
    }

    @Override
    public List<Trainee> getTrainees() {
        return traineeRepository.getTrainees();
    }

    private String generateUsername(User user) {
        String username = nameGenerator.generateUsername(user);

        if (traineeRepository.getByUsername(username).isEmpty() &&
                trainerRepository.getByUsername(username).isEmpty()) {
            return username;
        }

        return generateUsernameForMultipleCases(username);
    }

    private String generateUsernameForMultipleCases(String username){
        List<String> usernames = Stream.concat(
                        traineeRepository.getAllTraineeUsernamesByUsername(username).stream(),
                        trainerRepository.getAllTrainerUsernamesByUsername(username).stream())
                .toList();

        return nameGenerator.generateUsername(username, usernames);
    }
}