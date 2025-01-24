package io.w4t3rcs.generator.repository;

public interface FileRepository<T, ID> {
    ID save(T entity);

    T findById(ID id);

    void deleteById(ID id);
}
