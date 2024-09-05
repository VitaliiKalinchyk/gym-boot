package epam.task.gymboot.storage.impl;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@Component
public class InMemoryStorage implements Storage {
    private Map<Integer, Trainee> trainees = new ConcurrentHashMap<>();
    private Map<Integer, Trainer> trainers = new ConcurrentHashMap<>();
    private Map<Integer, Training> trainings = new ConcurrentHashMap<>();
}