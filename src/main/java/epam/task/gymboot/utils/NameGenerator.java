package epam.task.gymboot.utils;

import epam.task.gymboot.entity.User;

import java.util.List;

public interface NameGenerator {

    String generateUsername(User user);

    String generateUsername(String username, List<String> existingUsername);

}