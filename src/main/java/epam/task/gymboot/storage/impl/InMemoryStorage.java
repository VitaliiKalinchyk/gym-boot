package epam.task.gymboot.storage.impl;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class InMemoryStorage implements Storage {
    private final Map<Integer, Trainee> trainees = new ConcurrentHashMap<>();
    private final Map<Integer, Trainer> trainers = new ConcurrentHashMap<>();
    private final Map<Integer, Training> trainings = new ConcurrentHashMap<>();
}