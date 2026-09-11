package gp.jose.practice.reserveration.meetindAndBooking.factory;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

public interface UserFactory <U extends UserInterface> {
    U create(String fio, String login, String password);
}
