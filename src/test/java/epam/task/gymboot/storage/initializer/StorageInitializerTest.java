package epam.task.gymboot.storage.initializer;

import epam.task.gymboot.entity.Trainee;
import epam.task.gymboot.entity.Trainer;
import epam.task.gymboot.entity.Training;
import epam.task.gymboot.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
public class StorageInitializerTest {

    @InjectMocks
    private StorageInitializer storageInitializer;

    @Mock
    private Storage storage;

    @Mock
    private Resource resource;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(storageInitializer, "resource", resource);
    }

    @Test
    public void testInitializeStorage() throws IOException {
        InputStream yamlInputStream = getClass().getClassLoader().getResourceAsStream("data/initialData.yml");

        when(resource.getInputStream()).thenReturn(yamlInputStream);
        when(storage.getTrainees()).thenReturn(new ConcurrentHashMap<>());
        when(storage.getTrainers()).thenReturn(new ConcurrentHashMap<>());
        when(storage.getTrainings()).thenReturn(new ConcurrentHashMap<>());

        storageInitializer.initializeStorage();

        Map<Integer, Trainee> trainees = storage.getTrainees();
        Map<Integer, Trainer> trainers = storage.getTrainers();
        Map<Integer, Training> trainings = storage.getTrainings();

        assertEquals(1, trainees.size());
        assertTrue(trainees.containsKey(1));
        assertEquals(1, trainers.size());
        assertTrue(trainers.containsKey(1));
        assertEquals(1, trainings.size());
        assertTrue(trainings.containsKey(1));
    }

    @Test
    public void testInitializeStorageHandlesIOException() throws IOException {
        when(resource.getInputStream()).thenThrow(new IOException("File not found"));

        storageInitializer.initializeStorage();

        verify(storage, never()).getTrainees();
        verify(storage, never()).getTrainers();
        verify(storage, never()).getTrainings();
    }
}