package gp.jose.practice.reserveration.meetindAndBooking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Equipment {
    PROJECTOR ("проектор"),
    SCREEN  ("экран"),
    WHITEBOARD  ("доска (маркерная)"),
    VIDEO_CONFERENCE ("оборудование для видеоконференций (камера/колонки)"),
    TV_SCREEN ("телевизор/монитор для презентаций"),
    PHONE  ("конференц-телефон");
    private final String description;


    private static Optional<Equipment> findByInput(String input) {
        if (input == null || input.isBlank()) return Optional.empty();
        String cleanInput = input.trim().toLowerCase();

        return Arrays.stream(Equipment.values())
                .filter(e -> e.name().toLowerCase().equals(cleanInput)
                        || e.getDescription().toLowerCase().equals(cleanInput))
                .findFirst();
    }

    public static Set<Equipment> findSetByInput(String input) {
        if (input == null || input.isBlank()) {
            return Set.of();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Equipment::findByInput)
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }
}
