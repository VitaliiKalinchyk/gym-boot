package epam.task.gymboot.config;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class StorageConfig {

    private final Storage storage;

    @Bean
    @DependsOn("storageInitializerImpl")
    public Map<Integer, Trainee> trainees() {
        return storage.getTrainees();
    }

    @Bean
    @DependsOn("storageInitializerImpl")
    public Map<Integer, Trainer> trainers() {
        return storage.getTrainers();
    }


    @Bean
    @DependsOn("storageInitializerImpl")
    public Map<Integer, Training> trainings() {
        return storage.getTrainings();
    }
}