package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository {
    void add(Resource resource);

    Optional<Resource> findByName(String name);

    List<Resource> findAll();
}
