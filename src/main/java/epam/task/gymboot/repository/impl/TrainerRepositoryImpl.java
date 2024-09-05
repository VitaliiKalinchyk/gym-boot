package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.User;
import epam.task.gymboot.repository.TrainerRepository;

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
public class TrainerRepositoryImpl implements TrainerRepository {
    private final Map<Integer, Trainer> trainers;


    @Override
    public Optional<Trainer> add(Trainer trainer) {
        int trainerId = trainer.getTrainerId();
        log.debug("Adding trainer with ID {}: {}", trainerId, trainer);
        trainers.put(trainerId, trainer);
        Optional<Trainer> result = getById(trainerId);
        log.debug("Trainer added with ID {}: {}", trainerId, result);
        return result;
    }

    @Override
    public Optional<Trainer> edit(Trainer trainer) {
        log.debug("Editing trainer with ID {}: {}", trainer.getTrainerId(), trainer);
        Optional<Trainer> oldTrainer = Optional.ofNullable(trainers.replace(trainer.getTrainerId(), trainer));
        log.debug("Trainer edited with ID {}: {}", trainer.getTrainerId(), oldTrainer);
        return oldTrainer;
    }

    @Override
    public Optional<Trainer> getById(int trainerId) {
        Optional<Trainer> trainer = Optional.ofNullable(trainers.get(trainerId));
        log.debug("Retrieved trainer with ID {}: {}", trainerId, trainer);
        return trainer;
    }

    @Override
    public Optional<Trainer> getByUsername(String username) {
        Optional<Trainer> trainer = trainers.values()
                                            .stream()
                                            .filter(t -> t.getUser().getUsername().equals(username))
                                            .findAny();
        log.debug("Retrieved trainer with username {}: {}", username, trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getTrainers() {
        List<Trainer> trainerList = new ArrayList<>(trainers.values());
        log.debug("Retrieved all trainers: {}", trainerList);
        return trainerList;
    }

    @Override
    public List<String> getAllTrainerUsernamesByUsername(String username) {
        List<String> usernames = trainers.values()
                                         .stream()
                                         .map(Trainer::getUser)
                                         .map(User::getUsername)
                                         .filter(name -> name.startsWith(username))
                                         .toList();
        log.debug("Retrieved trainers usernames starting with {}: {}", username, usernames);
        return usernames;
    }

    @Override
    public int getNextId() {
        int nextId = trainers.keySet()
                             .stream()
                             .max(Integer::compareTo)
                             .orElse(0) + 1;
        log.debug("Next trainer ID is {}", nextId);
        return nextId;
    }
}