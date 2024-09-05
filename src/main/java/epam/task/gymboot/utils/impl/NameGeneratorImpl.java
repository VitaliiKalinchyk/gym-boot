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
    public String generateUsername(User user, List<String> userNames) {
        String baseUsername = generateUsername(user);

        int maxIndex = userNames.stream()
                        .map(userName -> userName.replaceAll("\\D+?(\\d*)$", "$1"))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .max()
                        .orElse(0);

        return baseUsername + (maxIndex + 1);
    }
}