package epam.task.gymboot.repository.impl;

import epam.task.gymboot.entity.Training;
import epam.task.gymboot.repository.TrainingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TrainingRepositoryImpl implements TrainingRepository {

    private final Map<Integer, Training> trainings;

    @Override
    public Optional<Training> add(Training training) {
        int trainingId = training.getTrainingId();
        log.debug("Adding training with ID {}: {}", trainingId, training);
        trainings.put(trainingId, training);
        Optional<Training> result = getById(trainingId);
        log.debug("Training added with ID {}: {}", trainingId, result);
        return result;
    }

    @Override
    public Optional<Training> getById(int trainingId) {
        Training training = trainings.get(trainingId);
        if (training != null) {
            log.debug("Found training with ID {}: {}", trainingId, training);
        } else {
            log.debug("No training found with ID {}", trainingId);
        }
        return Optional.ofNullable(training);
    }

    @Override
    public List<Training> getTrainings() {
        List<Training> trainingList = new ArrayList<>(trainings.values());
        log.debug("Retrieving all trainings: {}", trainingList);
        return trainingList;
    }

    @Override
    public int getNextId() {
        int nextId = trainings.keySet()
                              .stream()
                              .max(Integer::compareTo)
                              .orElse(0) + 1;
        log.debug("Next training ID is {}", nextId);
        return nextId;
    }
}