package epam.task.gymboot.utils.impl;

import epam.task.gymboot.entity.User;
import epam.task.gymboot.utils.NameGenerator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class NameGeneratorImpl implements NameGenerator {
    @Override
    public String generateUsername(User user) {
        return Objects.requireNonNull(user.getFirstName(), "First name is null") + "."
                + Objects.requireNonNull(user.getLastName(), "Last name is null");
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