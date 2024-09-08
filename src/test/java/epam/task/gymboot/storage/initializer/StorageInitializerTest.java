package epam.task.gymboot.storage.initializer;

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

import java.io.IOException;
import java.io.InputStream;

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

        storageInitializer.initializeStorage();

        verify(storage).setTrainees(argThat(map -> map.size() == 1 && map.containsKey(1)));
        verify(storage).setTrainers(argThat(map -> map.size() == 1 && map.containsKey(1)));
        verify(storage).setTrainings(argThat(map -> map.size() == 1 && map.containsKey(1)));
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