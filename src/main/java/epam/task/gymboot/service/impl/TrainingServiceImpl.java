package epam.task.gymboot.service.impl;

import epam.task.gymboot.entity.Training;
import epam.task.gymboot.repository.TrainingRepository;
import epam.task.gymboot.service.TrainingService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> add(Training training) {
        return trainingRepository.add(training);
    }

    @Override
    public Optional<Training> getById(int trainingId) {
        return trainingRepository.getById(trainingId);
    }

    @Override
    public List<Training> getTrainings() {
        return trainingRepository.getTrainings();
    }
}