package gp.jose.practice.reserveration.meetindAndBooking.repository.user;

import gp.jose.practice.reserveration.meetindAndBooking.model.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public final class UsersRepository <U extends UserInterface> implements UserRepositoryInterface<U> {

    private final HashMap<String, U> users = new HashMap<>();

    @Override
    public List<U> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(U user) {
        users.put(user.login(), user);
    }

    @Override
    public Optional<U> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

}
