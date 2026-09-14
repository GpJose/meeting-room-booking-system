package gp.jose.practice.reserveration.meetindAndBooking.model.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class User
        implements UserInterface {
    private final String FIO;
    private final String login;
    private final String password;

    public User(String FIO,
                String login,
                String password) {
        this.FIO = FIO;
        this.login = login;
        this.password = password;
    }

    @NotNull
    @Override
    public String toString() {
        return this.FIO;
    }

    @Override
    public String getFio() {
        return FIO;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.FIO, that.FIO) &&
                Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FIO, login, password);
    }

}
