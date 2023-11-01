package gateway;

import entities.Entity;

import java.util.UUID;
import java.util.function.Predicate;

public interface IRepository<T> {
    void addEntity(T entity);
    T getById(UUID id);
    T searchByFilter(Predicate<? super T> p);
}
