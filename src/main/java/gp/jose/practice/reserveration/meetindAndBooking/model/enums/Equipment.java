package gp.jose.practice.reserveration.meetindAndBooking.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
