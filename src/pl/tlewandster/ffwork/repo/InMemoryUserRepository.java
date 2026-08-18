package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.User;

import java.util.List;
import java.util.Optional;

public final class InMemoryUserRepository implements UserRepository {
    private final List<User> users;

    public InMemoryUserRepository(List<User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
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
