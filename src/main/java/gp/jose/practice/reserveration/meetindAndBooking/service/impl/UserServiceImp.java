package gp.jose.practice.reserveration.meetindAndBooking.service.impl;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;
import gp.jose.practice.reserveration.meetindAndBooking.service.UserServiceInterface;

public class UserServiceImp <U extends UserInterface> implements UserServiceInterface<U> {

    private UserServiceImp(){
    }

    private U user;

    @Override
    public boolean auth(String login, String password) {
        return false;
    }

    @Override
    public boolean create(String login, String password) {
        return false;
    }

    @Override
    public U getUser() {
        return this.user;
    }
}
