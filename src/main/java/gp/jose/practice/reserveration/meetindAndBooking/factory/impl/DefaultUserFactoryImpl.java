package gp.jose.practice.reserveration.meetindAndBooking.factory.impl;

import gp.jose.practice.reserveration.meetindAndBooking.factory.UserFactory;
import gp.jose.practice.reserveration.meetindAndBooking.model.impl.User;

public class DefaultUserFactoryImpl implements UserFactory<User> {

    @Override
    public User create(String fio, String login, String password) {
        return new User(fio, login, password);
    }
}
