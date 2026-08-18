package pl.tlewandster.ffwork.repo;

import pl.tlewandster.ffwork.domain.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryResourceRepository implements ResourceRepository {
    private final List<Resource> resources;

    public InMemoryResourceRepository(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public void add(Resource resource) {
        if (findByName(resource.getName()).isPresent()) {
            throw new IllegalArgumentException("Resource with name " + resource.getName() + " already exists");
        }
        resources.add(resource);
    }

    @Override
    public Optional<Resource> findByName(String name) {
        return resources.stream()
                .filter(resource -> resource.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Resource> findAll() {
        return List.copyOf(resources);
    }
}
