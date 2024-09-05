package epam.task.gymboot.utils.impl;

import epam.task.gymboot.entity.User;
import epam.task.gymboot.utils.NameGenerator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameGeneratorImpl implements NameGenerator {
    @Override
    public String generateUsername(User user) {
        return user.getFirstName() + "." + user.getLastName();
    }

    @Override
    public String generateUsername(String username, List<String> existingUsername) {
        int maxIndex = existingUsername.stream()
                        .map(userName -> userName.replaceAll("\\D+?(\\d*)$", "$1"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .max()
                        .orElse(0);

        return username + (maxIndex + 1);
    }
}