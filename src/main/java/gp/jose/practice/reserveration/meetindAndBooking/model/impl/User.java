package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class User implements UserInterface {

    private Long id;
    private String FIO;
    private String login;
    private String password;

    @Override
    public Long getUserId() {
        return this.id;
    }

    @Override
    public boolean equals(String login, String password) {
        return this.login.equalsIgnoreCase(login) && this.password.equals(password);
    }

    @Override
    public String getLogin(String login) {
        return this.login;
    }

}
