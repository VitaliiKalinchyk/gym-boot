package epam.task.gymboot.storage.initializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class StorageInitializer {

    @Value("${file.path.initialData}")
    private Resource resource;

    private final Storage storage;

    @PostConstruct
    public void initializeStorage() {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        om.registerModule(new JavaTimeModule());
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        try (InputStream inputStream = resource.getInputStream()) {
            log.info("Initializing storage from YAML file: {}", resource.getFilename());

            YamlDataContainer root = om.readValue(inputStream, YamlDataContainer.class);

            storage.setTrainees(root.getTrainees().stream()
                    .collect(Collectors.toMap(Trainee::getTraineeId, Function.identity())));

            storage.setTrainers(root.getTrainers().stream()
                    .collect(Collectors.toMap(Trainer::getTrainerId, Function.identity())));

            storage.setTrainings(root.getTrainings().stream()
                    .collect(Collectors.toMap(Training::getTrainingId, Function.identity())));

            log.info("Storage successfully initialized with {} trainees, {} trainers, and {} trainings.",
                    root.getTrainees().size(), root.getTrainers().size(), root.getTrainings().size());

        } catch (IOException e) {
            log.error("Error initializing storage from YAML file: {}", resource.getFilename(), e);
        }
    }
}