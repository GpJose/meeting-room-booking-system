package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

public interface UserServiceInterface <U extends UserInterface> {
    boolean auth(String login, String password);
    boolean create(String fio, String login, String password);
    U getUser();
}
