package gp.jose.practice.reserveration.meetindAndBooking.service;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.Optional;

public interface UserServiceInterface <U extends UserInterface> {
    Optional<U> auth(String login, String password);
    void create(String fio, String login, String password);
}
