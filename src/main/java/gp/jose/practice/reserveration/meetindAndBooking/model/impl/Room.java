package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.enums.Equipment;
import gp.jose.practice.reserveration.meetindAndBooking.model.RoomInterface;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

//  название, вместимость, список оборудования (проектор, экран и т.д.).
@Getter
@Builder
@EqualsAndHashCode
public class Room implements RoomInterface {
    protected Long roomId;
    protected String roomName;
    protected String capacity;
    protected List<Equipment> equipments;

}
