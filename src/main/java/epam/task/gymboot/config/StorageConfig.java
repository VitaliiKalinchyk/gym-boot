package epam.task.gymboot.config;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class StorageConfig {

    private final Storage storage;

    @Bean
    public Map<Integer, Trainee> trainees() {
        return storage.getTrainees();
    }

    @Bean
    public Map<Integer, Trainer> trainers() {
        return storage.getTrainers();
    }


    @Bean
    public Map<Integer, Training> trainings() {
        return storage.getTrainings();
    }
}