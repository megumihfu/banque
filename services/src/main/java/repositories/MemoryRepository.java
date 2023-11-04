package repositories;

import entities.*;
import gateway.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class MemoryRepository<T extends Entity> implements IRepository<T> {
    private final List<T>  entities = new ArrayList<>();

    public void addEntity(T entity) {
        entities.add(entity);
    }

    /**
     * search in the list for the Entity with the id given as parameter
     * @param id unique identifier of the entity
     * @return the entity or null if not found
     */
    public T getById(UUID id) {
        for (T e : entities) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public T searchByFilter(Predicate<? super T> p) {
        return entities.stream().filter(p).findAny().orElse(null);
    }
}
