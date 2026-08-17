package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void add(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();
}
