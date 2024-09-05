package epam.task.gymboot.utils;

import epam.task.gymboot.entity.User;

import java.util.List;

public interface NameGenerator {

    String generateUsername(User user);

    String generateUsername(User user, List<String> userNames);

}