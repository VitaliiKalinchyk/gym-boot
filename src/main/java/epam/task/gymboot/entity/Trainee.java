package epam.task.gymboot.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Trainee {
    private int traineeId;
    private User user;
    private LocalDate birthday;
    private String address;
}