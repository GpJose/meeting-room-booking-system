package gp.jose.practice.reserveration.meetindAndBooking.repository;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.TreeSet;

public interface UserRepositoryInterface <U extends UserInterface> {
    TreeSet<U> findAll();
    boolean create(U user);
}
