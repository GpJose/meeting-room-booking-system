package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

public record User(String FIO,
                   String login,
                   String password)
        implements UserInterface {
}
