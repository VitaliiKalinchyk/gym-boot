package epam.task.gymboot.utils.impl;

import epam.task.gymboot.utils.NameGenerator;
import epam.task.gymboot.entity.User;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class NameGeneratorImplTest {

    @Autowired
    private  NameGenerator nameGenerator;

    @Test
    public void testGenerateUsernameWithoutExistingUsernames() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        String generatedUsername = nameGenerator.generateUsername(user);

        assertEquals("John.Doe", generatedUsername);
    }

    @Test
    public void testGenerateUsernameFirstNameNull() {
        User user = new User();

        NullPointerException e = assertThrows(NullPointerException.class, () -> nameGenerator.generateUsername(user));

        assertEquals("First name is null", e.getMessage());
    }

    @Test
    public void testGenerateUsernameLastNameNull() {
        User user = new User();
        user.setFirstName("John");

        NullPointerException e = assertThrows(NullPointerException.class, () -> nameGenerator.generateUsername(user));

        assertEquals("Last name is null", e.getMessage());
    }

    @Test
    public void testGenerateUsernameWithExistingUsernames() {
        List<String> existingUsernames = List.of("John.Doe1", "John.Doe2");

        String generatedUsername = nameGenerator.generateUsername("John.Doe", existingUsernames);

        assertEquals("John.Doe3", generatedUsername);
    }

    @Test
    public void testGenerateUsernameWithNoExistingIndexes() {
        List<String> existingUsernames = List.of("John.Doe");

        String generatedUsername = nameGenerator.generateUsername("John.Doe", existingUsernames);

        assertEquals("John.Doe1", generatedUsername);
    }
}