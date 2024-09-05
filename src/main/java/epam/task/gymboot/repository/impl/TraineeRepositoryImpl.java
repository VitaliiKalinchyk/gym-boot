package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TraineeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TraineeRepositoryImpl implements TraineeRepository {

    private final Map<Integer, Trainee> trainees;

    @Override
    public Optional<Trainee> add(Trainee trainee) {
        int traineeId = trainee.getTraineeId();
        log.debug("Adding trainee with ID {}: {}", traineeId, trainee);
        trainees.put(traineeId, trainee);
        Optional<Trainee> result = getById(traineeId);
        log.debug("Trainee added with ID {}: {}", traineeId, result);
        return result;
    }

    @Override
    public Optional<Trainee> edit(Trainee trainee) {
        log.debug("Editing trainee with ID {}: {}", trainee.getTraineeId(), trainee);
        Optional<Trainee> oldTrainee = Optional.ofNullable(trainees.replace(trainee.getTraineeId(), trainee));
        log.debug("Trainee edited with ID {}: {}", trainee.getTraineeId(), oldTrainee);
        return oldTrainee;
    }

    @Override
    public boolean delete(int traineeId) {
        log.debug("Deleting trainee with ID {}", traineeId);
        boolean result = trainees.remove(traineeId) != null;
        log.debug("Trainee deleted with ID {}: {}", traineeId, result);
        return result;
    }

    @Override
    public Optional<Trainee> getById(int traineeId) {
        Optional<Trainee> trainee = Optional.ofNullable(trainees.get(traineeId));
        log.debug("Retrieved trainee with ID {}: {}", traineeId, trainee);
        return trainee;
    }

    @Override
    public Optional<Trainee> getByUsername(String username) {
        Optional<Trainee> trainee = trainees.values()
                                            .stream()
                                            .filter(t -> t.getUser().getUsername().equals(username))
                                            .findAny();
        log.debug("Retrieved trainee with username {}: {}", username, trainee);
        return trainee;
    }

    @Override
    public List<Trainee> getTrainees() {
        List<Trainee> traineeList = new ArrayList<>(trainees.values());
        log.debug("Retrieved all trainees: {}", traineeList);
        return traineeList;
    }

    @Override
    public List<String> getAllTraineeUsernamesByUsername(String username) {
        List<String> usernames = trainees.values()
                                         .stream()
                                         .map(Trainee::getUser)
                                         .map(User::getUsername)
                                         .filter(name -> name.startsWith(username))
                                         .toList();
        log.debug("Retrieved trainees usernames starting with {}: {}", username, usernames);
        return usernames;
    }

    @Override
    public int getNextId() {
        int nextId = trainees.keySet()
                             .stream()
                             .max(Integer::compareTo)
                             .orElse(0) + 1;
        log.debug("Next trainee ID is {}", nextId);
        return nextId;
    }
}