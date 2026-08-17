package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users);
    }
}
