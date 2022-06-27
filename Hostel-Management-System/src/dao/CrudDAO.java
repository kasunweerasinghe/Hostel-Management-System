package dao;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{
    List<T> getAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(ID id);

    boolean exist(ID id);

    T search(ID id);
}
