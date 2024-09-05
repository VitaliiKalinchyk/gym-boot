package epam.task.gymboot.utils.impl;

import epam.task.gymboot.utils.PasswordGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordGeneratorImplTest {

    @Autowired
    PasswordGenerator passwordGenerator;

    @Value("${password.length}")
    private int passwordLength;

    @Test
    public void testGeneratePasswordCorrectLength() {
        String password = passwordGenerator.generatePassword();

        assertEquals(passwordLength, password.length());
    }

    @Test
    public void testGeneratePasswordContainsRequiredCharacters() {
        String password = passwordGenerator.generatePassword();

        assertTrue(password.chars().anyMatch(Character::isUpperCase),
                "Password should contain at least one uppercase letter");
        assertTrue(password.chars().anyMatch(Character::isLowerCase),
                "Password should contain at least one lowercase letter");
        assertTrue(password.chars().anyMatch(Character::isDigit),
                "Password should contain at least one digit");
    }

    @Test
    public void testGeneratePasswordContainsOnlyAllowedCharacters() {
        String password = passwordGenerator.generatePassword();

        assertTrue(password.matches("[A-Za-z0-9]+"),
                "Password should only contain allowed characters (letters and digits)");
    }
}