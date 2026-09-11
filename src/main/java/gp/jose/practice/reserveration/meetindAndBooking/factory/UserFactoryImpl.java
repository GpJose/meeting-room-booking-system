package gp.jose.practice.reserveration.meetindAndBooking.factory;

import gp.jose.practice.reserveration.meetindAndBooking.model.impl.User;

public class UserFactoryImpl implements UserFactory<User> {
    @Override
    public User create(String fio, String login, String password) {
        return new User(fio, login, password);
    }
}
