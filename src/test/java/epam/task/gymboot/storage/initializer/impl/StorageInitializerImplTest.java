package epam.task.gymboot.storage.initializer.impl;

import epam.task.gymboot.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class StorageInitializerImplTest {

    @InjectMocks
    private StorageInitializerImpl storageInitializer;

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
        when(resource.getInputStream()).thenReturn(getYamlData());

        storageInitializer.initializeStorage();

        verify(storage).setTrainees(argThat(map -> map.size() == 1 && map.containsKey(1)));
        verify(storage).setTrainers(argThat(map -> map.size() == 1 && map.containsKey(1)));
        verify(storage).setTrainings(argThat(map -> map.size() == 1 && map.containsKey(1)));
    }

    private static InputStream getYamlData() {
        String yamlData = """
                ---
                trainees:
                - traineeId: 1
                  user:
                    userId: 1
                    firstName: Karl
                    lastName: Gunner
                    username: Karl.Gunner
                    password: A1s12DytRd
                    active: TRUE
                  birthday: 1999-10-01
                  address: Springfield, Valentine st., 4
                trainers:
                - trainerId: 1
                  user:
                    userId: 2
                    firstName: Karl
                    lastName: Gunner
                    username: Karl.Gunner1
                    password: JJFzh6qC9y
                    active: TRUE
                  trainingType: YOGA
                trainings:
                - trainingId: 1
                  traineeId: 1
                  trainerId: 2
                  name: first training
                  trainingType: ZUMBA
                  date: 2024-08-17
                  durationMinutes: 60
                """;

        return new ByteArrayInputStream(yamlData.getBytes());
    }

    @Test
    public void testInitializeStorageHandlesIOException() throws IOException {
        when(resource.getInputStream()).thenThrow(new IOException("File not found"));

        storageInitializer.initializeStorage();

        verify(storage, never()).setTrainees(any());
        verify(storage, never()).setTrainers(any());
        verify(storage, never()).setTrainings(any());
    }
}