package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.utils.CryptUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class User implements UserInterface {
    private final String FIO;
    private final String login;

    private final String password;

    public User(String fio, String login, String password) {
        this.FIO = fio;
        this.login = login;
        this.password = password;
    }

    @Override
    public String getUserLogin() {
        return this.login;
    }
}
