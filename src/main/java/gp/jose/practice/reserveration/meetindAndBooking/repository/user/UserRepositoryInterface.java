package gp.jose.practice.reserveration.meetindAndBooking.repository.user;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface <U extends UserInterface> {
    List<U> findAll();
    void save(U user);
    Optional<U> findById(String id);
}
