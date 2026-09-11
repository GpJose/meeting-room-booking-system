package gp.jose.practice.reserveration.meetindAndBooking.repository;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.Comparator;
import java.util.TreeSet;

public class UsersRepository <U extends UserInterface> implements UserRepositoryInterface<U> {
    private  final TreeSet<U> users = new TreeSet<> (Comparator.comparing(UserInterface::getUserLogin));
    public TreeSet<U> findAll() {
        return users;
    }
    public boolean create(U user) {
        return users.add(user);
    }

}
